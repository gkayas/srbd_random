#! /bin/bash
dl=$1
OLD_VER=$(cat abi.conf | awk '/^OLD_VER:/ { print $2; exit; }')
if [ $? -ne 0 ]; then printf "\n${RED}[!]${NC}.......Aborting [Command failure: failed to load abi.conf].\n"; exit 1; fi
NEW_VER=$(cat abi.conf | awk '/^NEW_VER:/ { print $2; exit; }')
if [ $? -ne 0 ]; then printf "\n${RED}[!]${NC}.......Aborting [Command failure: failed to load abi.conf].\n"; exit 1; fi
SYMBOLS_ABI_AFFECTED=$(awk 'END{print NR}' compat_reports/$dl/${OLD_VER}_to_${NEW_VER}/abi_affected.txt)
SYMBOLS_SRC_AFFECTED=$(awk 'END{print NR}' compat_reports/$dl/${OLD_VER}_to_${NEW_VER}/src_affected.txt)

ABI_AFFECTED=0
SRC_AFFECTED=0
ABI_IS_COMPAT=1
SRC_IS_COMPAT=1

(chmod 777 verdict.sh; ./verdict.sh $dl $OLD_VER $NEW_VER)

ABI_IS_COMPAT=$(cat verdict.log | awk '{print $1}')
SRC_IS_COMPAT=$(cat verdict.log | awk '{print $2}')
if [ "$ABI_IS_COMPAT" -eq 0 ]; then		
	ABI_AFFECTED=$(cat affect.log | awk '{print $1}')
fi
if [ "$SRC_IS_COMPAT" -eq 0 ]; then
	SRC_AFFECTED=$(cat affect.log | awk '{print $2}')
fi

RESULT="<b>Compatible</b>"
BINARY="Compatible"
SOURCE="Compatible"
COLOR="green"
ABI_AFFECTED_COLOR="black"
SRC_AFFECTED_COLOR="black"
SRC_COLOR="green"
LINK="$dl/${OLD_VER}_to_${NEW_VER}/compat_report.html"

if [ -z "${SYMBOLS_ABI_AFFECTED}" -o -z "${SYMBOLS_SRC_AFFECTED}" ]; then
	RESULT="<b>Not reported</b>"
	COLOR="black"
	BINARY="--"
	SOURCE="--"
	LINK="#"
	ABI_AFFECTED="--"
	SRC_AFFECTED="--"
elif [ "$ABI_IS_COMPAT" -eq 0 ]; then
	COLOR="red"
	RESULT="<b>Incompatible</b>"
	BINARY="Incompatible"
	ABI_AFFECTED_COLOR="red"
	
	let INCOMPATIBLE_COUNT++
			
	if [ "$SRC_IS_COMPAT" -eq 0 ]; then
		SRC_AFFECTED_COLOR="red"
		SRC_COLOR="red"
		SOURCE="Incompatible"
	elif [ "$SRC_IS_COMPAT" -eq 1 ]; then
		SRC_AFFECTED_COLOR="black"
		SOURCE="Compatible"
		SRC_COLOR="green"
	fi			
elif [ "$ABI_IS_COMPAT" -eq 1 ]; then
	COLOR="green"
	RESULT="<b>Compatible</b>"
	
	ABI_AFFECTED_COLOR="black"
	SRC_AFFECTED_COLOR="black"
	SRC_COLOR="green"
	SOURCE="Compatible"
fi

PKG2GIT_MAP=$(grep "${dl%%[[:space:]]}[- 0-9]*.armv7l" old-pkg-mod.list | awk '{print $3}')
for item in `echo $PKG2GIT_MAP`
do
	echo "${dl%%\*},${item}" >> pkg2git-map.list
	break;
done

echo "
	<tr>
	<td><a href=\"${LINK}\">$dl</a></td>
	<td style=\"color:${ABI_AFFECTED_COLOR}\">${ABI_AFFECTED}</td>
	<td style=\"color:${SRC_AFFECTED_COLOR}\">${SRC_AFFECTED}</td>
	<td style=\"color:${COLOR}\">${BINARY}</td>
	<td style=\"color:${SRC_COLOR}\">${SOURCE}</td>
	<td style=\"color:${COLOR}\">${RESULT}</td>
	</tr>" >> compat_reports/index.html	
