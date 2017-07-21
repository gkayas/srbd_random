#! /bin/bash
OLD_VER=$(cat abi.conf | awk '/^OLD_VER:/ { print $2; exit; }')
if [ $? -ne 0 ]; then printf "\n${RED}[!]${NC}.......Aborting [Command failure: failed to load abi.conf].\n"; exit 1; fi
NEW_VER=$(cat abi.conf | awk '/^NEW_VER:/ { print $2; exit; }')
if [ $? -ne 0 ]; then printf "\n${RED}[!]${NC}.......Aborting [Command failure: failed to load abi.conf].\n"; exit 1; fi
BASE_BINARY=$(cat abi.conf | awk '/^BASE_BINARY:/ { print $2; exit; }')
if [ $? -ne 0 ]; then printf "\n${RED}[!]${NC}.......Aborting [Command failure: failed to load abi.conf].\n"; exit 1; fi
COMPARED_BINARY=$(cat abi.conf | awk '/^COMPARED_BINARY:/ { print $2; exit; }')
if [ $? -ne 0 ]; then printf "\n${RED}[!]${NC}.......Aborting [Command failure: failed to load abi.conf].\n"; exit 1; fi
echo "	
<!DOCTYPE html>
<html>
<head>
<style>
table, th, td {
	border: 1px solid black;
}
</style>
<title>ABI Compatibility Report | ${OLD_VER} to ${NEW_VER}</title>
</head>
<body>
<h1>ABI Compatibility Reports Of $OLD_VER to $NEW_VER Packages</h1>
<p>
<table style=\"width:60%\">
<tr>
	<td colspan=\"6\">
		<p>Base binary		: ${BASE_BINARY}</p>
		<p>Compared binary	: ${COMPARED_BINARY}</p>
		<p><a href=\"#summary\">See the Summary Report Statistics at the end of this page</a></p>
	</td>
</tr>
<tr>
	<th><b>Packages</b></th>
	<th>ABI Affected (%)</th>
	<th>Source Affected (%)</th>
	<th>Binary</th>
	<th>Source</th>
	<th><b>Result</b></th>
</tr>" > compat_reports/index.html
