#! /bin/sh

#[Input Parameter]
AUT=/home/kallol/tizen-sdk/ide


RUNNER=$(pwd)/../rcptt.runner-2.1.0/eclipse
PROJECT=$(pwd)/../old_ux

testResults=$PROJECT/../results
rcpttWorkspace=$PROJECT/../rcptt.workspace
runnerWorkspace=$rcpttWorkspace/runner-workspace
autWorkspace=$rcpttWorkspace/aut-workspace-
autOut=$rcpttWorkspace/aut-out-
junitReport=$rcpttWorkspace/results.xml
htmlReport=$rcpttWorkspace/results.html
faillist=$PROJECT/../results/faillist


if [ ! -d "$rcpttWorkspace" ]; then
  mkdir "$rcpttWorkspace"
fi


if [ ! -d "$testResults" ]; then
  mkdir "$testResults"
fi


module=""
option=$1

if [ "$option" = "sp" ]; then 
	echo "Initializing SetupPrerequisite..."  
    
    java -jar $RUNNER/plugins/org.eclipse.equinox.launcher_1.3.200.v20160318-1642.jar \
     -application org.eclipse.rcptt.runner.headless \
     -data $runnerWorkspace \
     -aut $AUT \
     -autWsPrefix $autWorkspace \
     -autConsolePrefix $autOut \
     -autVMArgs -Xmx1024m \
     -autVMArgs -XX:MaxPermSize=1024m \
     -reuseExistingWorkspace \
     -junitReport $junitReport \
     -import $PROJECT \
	 -tests SetupPrerequisite.test 

	echo "SetupPrerequisite completed.Please make sure SetupPrerequisite is passed..."  

elif [ "$option" = "run" ]; then 
	type=$2
	if [ "$type" = "suite" ]; then 	
		if [ ! -f "./suitelist" ]; then
		  cwd=$(pwd)
		  echo "$cwd/suitelist file does not exist..."
		  exit
		fi
		
		suitelist=$(cat ./suitelist)
		cwd=$(pwd)
		if [ "$suitelist" = "" ]; then
			echo "$cwd/suitelist file is empty..."
		  exit
		fi
		
		#suitelist=$(sed ':a;N;$!ba;s/\n/;/g' ./module)
		
		for suitelist in $(cat ./suitelist)
		do
		echo "\nStart executing suitelist...\n"

		java -jar $RUNNER/plugins/org.eclipse.equinox.launcher_1.3.200.v20160318-1642.jar \
		 -application org.eclipse.rcptt.runner.headless \
		 -data $runnerWorkspace \
		 -aut $AUT \
		 -autWsPrefix $autWorkspace \
		 -autConsolePrefix $autOut \
		 -autVMArgs -Xmx1024m \
		 -autVMArgs -XX:MaxPermSize=1024m \
		 -reuseExistingWorkspace \
		 -junitReport $junitReport \
		 -import $PROJECT \
		 -suites "$suitelist"
		 
		java -jar report-parser.jar run $suitelist		 
		done
	elif [ "$type" = "tc" ]; then 
		if [ ! -f "./tclist" ]; then
		  cwd=$(pwd)
		  echo "$cwd/tclist file does not exist..."
		  exit
		fi
		
		tc_list=$(cat "./tclist")
		if [ "$tc_list" = "" ]; then
			cwd=$(pwd)
			echo "$cwd/tclist file is empty..."
		  exit
		fi
		
		tc_list=$(sed ':a;N;$!ba;s/\n/.test;/g' "./tclist" | awk '{print $0".test"}')
		
		echo "\nStart executing  tclist...\n"
		
		java -jar $RUNNER/plugins/org.eclipse.equinox.launcher_1.3.200.v20160318-1642.jar \
		 -application org.eclipse.rcptt.runner.headless \
		 -data $runnerWorkspace \
		 -aut $AUT \
		 -autWsPrefix $autWorkspace \
		 -autConsolePrefix $autOut \
		 -autVMArgs -Xmx1024m \
		 -autVMArgs -XX:MaxPermSize=1024m \
		 -reuseExistingWorkspace \
		 -junitReport $junitReport \
		 -import $PROJECT \
		 -tests "$tc_list"
		
		java -jar report-parser.jar run " "
	else
		echo "Incorrect Parameter.(ex: ./runner.sh run suite/tc)"  
		exit
    fi

elif [ "$option" = "rerun" ]; then 
	if [ ! -f "$faillist" ]; then
	  echo "$faillist file does not exist..."
	  exit
	fi
	
	failed_tc=$(cat "$faillist")
	if [ "$failed_tc" = "" ]; then
		echo "$faillist file is empty..."
	  exit
	fi
	
	failed_tc=$(sed ':a;N;$!ba;s/\n/.test;/g' "$faillist" | awk '{print $0".test"}')
	
	echo "\nStart executing  faillist...\n"
    
    java -jar $RUNNER/plugins/org.eclipse.equinox.launcher_1.3.200.v20160318-1642.jar \
     -application org.eclipse.rcptt.runner.headless \
     -data $runnerWorkspace \
     -aut $AUT \
     -autWsPrefix $autWorkspace \
     -autConsolePrefix $autOut \
     -autVMArgs -Xmx1024m \
     -autVMArgs -XX:MaxPermSize=1024m \
     -reuseExistingWorkspace \
     -junitReport $junitReport \
     -import $PROJECT \
	 -tests "$failed_tc"
	
	java -jar report-parser.jar rerun
	 	 
elif [ "$option" = "clear" ]; then 
	rm -rf "$testResults"
	rm -rf "$rcpttWorkspace"
else
    echo "Incorrect Parameter.(ex: ./runner.sh sp/run/rerun/clear)"  
    exit
fi

