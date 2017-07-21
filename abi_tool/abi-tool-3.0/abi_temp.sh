
#~ Black        0;30     Dark Gray     1;30
#~ Red          0;31     Light Red     1;31
#~ Green        0;32     Light Green   1;32
#~ Brown/Orange 0;33     Yellow        1;33
#~ Blue         0;34     Light Blue    1;34
#~ Purple       0;35     Light Purple  1;35
#~ Cyan         0;36     Light Cyan    1;36
#~ Light Gray   0;37     White         1;37
RED='\033[0;31m'
GREEN='\033[0;32m'
NC='\033[0m' # No Color

SYS_ROOT=$(cat abi.conf | awk '/^SYS_ROOT:/ { print $2; exit; }')
if [ $? -ne 0 ]; then printf "\n${RED}[!]${NC}.......Aborting [Command failure: failed to load abi.conf].\n"; exit 1; fi
NEW_RPM_DIR=$(cat abi.conf | awk '/^NEW_PKG_DOWNLOAD_DIR:/ { print $2; exit; }')
if [ $? -ne 0 ]; then printf "\n${RED}[!]${NC}.......Aborting [Command failure: failed to load abi.conf].\n"; exit 1; fi

function copy_to_root_strap()
{

	for rpm in `ls $NEW_RPM_DIR | grep devel-`
	do
		(cd $NEW_RPM_DIR; rpm2cpio $rpm | cpio -idmv; cd .. )			
		if [ $? -ne 0 ]; then printf "\n${RED}[!]${NC}.......Rootstrap $rpm rpm extraction failed. [Command failure].\n"; fi
		for file in `ls $NEW_RPM_DIR/usr/include`
		do 
			echo $file
			mv $NEW_RPM_DIR/usr/include/$file $NEW_RPM_DIR/usr/include/_$file
			if [ $? -ne 0 ]; then 
				printf "\n${RED}[!]${NC}.......Move to SYS_ROOT Failed [Command failure]. \n"; 
			fi
			cp -r -n $NEW_RPM_DIR/usr/include/ ~/$SYS_ROOT/usr/
			if [ $? -ne 0 ]; then 
				printf "\n${RED}[!]${NC}.......rm -rf failed [Command failure].\n"; 
			fi
		done
		rm -rf $NEW_RPM_DIR/usr/
	done

}

copy_to_root_strap
