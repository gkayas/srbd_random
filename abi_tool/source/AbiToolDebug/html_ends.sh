#! /bin/bash
INCOMPATIBLE_COUNT=0
PKGS_REPORTED=$(find ./compat_reports/. -type d -name lib* | awk 'END{print NR}')

COMPATIBILITY_RATE=0
if [ $PKGS_REPORTED -gt 0 ]; then
	COMPATIBILITY_RATE=$(bc -l <<< "scale = 2; $INCOMPATIBLE_COUNT / $PKGS_REPORTED")
fi
	
echo "
	<tr>
		<td id=\"summary\" colspan=\"6\">
			<p>Total packages : ${PKGS_REPORTED}</p>
			<p>Incompatible packages : ${INCOMPATIBLE_COUNT}</p>
			<p>Incompatible rate : ${COMPATIBILITY_RATE}</p>
		</td>
	</tr>
	</table>
	</p>
	</body>
	</html>" >> compat_reports/index.html
	
printf "\n${GREEN}[#]${NC}.......INDEX PAGE IS PREPARED TO: \"compat_reports/index.html\"."
	
printf "\n${RED}[!]${NC}.......Please check for any missing platform headers exists to: logs/missing_headers.log."
echo "====================" >> logs/missing_headers.log
date >> logs/missing_headers.log
echo "====================" >> logs/missing_headers.log
(grep -Hnr 'fatal error' ./logs --exclude=missing_headers.log >> logs/missing_headers.log)
	
printf "\n${GREEN}[#]${NC}.......STARTED TO GENERATE SUMMARY REPORT."
(chmod 777 rpt-gen.sh; ./rpt-gen.sh)
if [ $? -ne 0 ]; then printf "\n${RED}[!]${NC}.......Aborting [Command failure].\n"; remove -temp; exit 1; fi
printf "\n${GREEN}[#]${NC}.......Summary report of ABI compatibility reports is generated to: \"compat_reports/report_summary.xls\".\n"

remove -temp


function remove()
{
	if [ "$1" == "-reports" ]; then
		find . -type d -name compat_reports -exec rm -rf {} \+
	elif [ "$1" == "-temp" ]; then
		rm -rf old-pkg-mod.list
		rm -rf new-pkg-mod.list
		rm -rf pkg2git-map.list
		rm -rf logs/downloaded_pkgs.log
		rm -rf verdict.log
		rm -rf affect.log
	#~elif [ "$1" == "-pkg-list" ]; then
		#rm -rf old-pkg.list
		#rm -rf new-pkg.list
	elif [ "$1" == "-rpm" ]; then
		find . -type d -wholename "$NEW_RPM_DIR" -exec rm -rf {} \+
		find . -type d -wholename "$OLD_RPM_DIR" -exec rm -rf {} \+
	elif [ "$1" == "-pkg-comp" ]; then
		find $NEW_RPM_DIR/* -type d -exec rm -rf {} \+
		find $OLD_RPM_DIR/* -type d -exec rm -rf {} \+
		rm -rf $OLD_RPM_DIR/ABI-old.dump 
		rm -rf $NEW_RPM_DIR/ABI-new.dump 
	else
		printf "\n${RED}[!]${NC}.......Nothing Removed."
	fi
}
