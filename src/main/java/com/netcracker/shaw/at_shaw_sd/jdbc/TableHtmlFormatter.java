package com.netcracker.shaw.at_shaw_sd.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/* Format of html
<table border=1px>
<thead>
	<tr><th>Sl</th><th>Heading</th><th>Heading</th></tr>
</thead>
<tbody>
	<tr><td>1</td><td>Body Row 1</td><td>Body Row 1</td></tr>
	<tr><td>2</td><td>Body Row 1</td><td>Body Row 1</td></tr>
	<tr><td>3</td><td>Body Row 1</td><td>Body Row 1</td></tr>
	<tr><td>4</td><td>Body Row 1</td><td>Body Row 1</td></tr>
</tbody>
</table>*/

public class TableHtmlFormatter {
	public String formatToHTML(ResultSet rs) throws SQLException {
		int columnCount = rs.getMetaData().getColumnCount();
		String table = "<table><thead><tr><th>Sl</th>";
		
		for (int i = 1; i <= columnCount; i++) {
			table += "<th>" + rs.getMetaData().getColumnName(i) + "</th>";
		}
		
		table += "</tr></thead><tbody>";
		
		int count = 1;
		while(rs.next()) {
			table += "<tr>";
			
			table += "<td>" + count + "</td>";
			
			for (int i = 1; i <= columnCount; i++) {
				table += "<td>" + rs.getString(i) + "</td>";
			}
			table += "</tr>";
			count++;
		}
		table += "</tbody></table>";
		return table;
	}
}
