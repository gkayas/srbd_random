for line in `cat modules_git_repo.txt`
do
	name=${line%%[[:space:]]}
	name=$(echo $name | sed "s/\[/\\\[/g" | sed "s/\]/\\\]/g")
	echo $name;
	grep "$name#" old-pkg.list>>old-pkg-mod.list
done
