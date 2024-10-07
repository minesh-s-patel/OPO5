<%

String state = (String)session.getAttribute("state");
if (state == null) state = "";
%>

<%!
  String makeTabs(String name, String OnOffstate) {
  String OnOrOff = "off";
  String colour = "#0000CC";

  String URL = "/Organiser_main/"+ name +"/"+ name +".jsp";

  if (OnOffstate.equals(name))
    OnOrOff = "on";

  if (OnOrOff.equals("on"))
    colour = "#D1E3F3";

  String HTMLReturn = "";

  HTMLReturn = "<td>";
  HTMLReturn = HTMLReturn + "<img src='/OPO5/Organiser_main/Organiser_files/menu.start."+ OnOrOff +".gif'>";
  HTMLReturn = HTMLReturn + "</td>";
  HTMLReturn = HTMLReturn + "<td noWrap background='/OPO5/Organiser_main/Organiser_files/menu.back."+ OnOrOff +".gif'>";
  HTMLReturn = HTMLReturn + "<a href='/OPO5/Organiser_main/"+ name +"/"+ name +"'>";
  HTMLReturn = HTMLReturn + "<b>";
  HTMLReturn = HTMLReturn + "<font face='tahoma,sans-serif' color='"+ colour +"' size='1'>";
  HTMLReturn = HTMLReturn + name + "";
  HTMLReturn = HTMLReturn + "</font></b></a>&nbsp;";
  HTMLReturn = HTMLReturn + "</td>";
  HTMLReturn = HTMLReturn + "<td>";
  HTMLReturn = HTMLReturn + "<img src='/OPO5/Organiser_main/Organiser_files/menu.end."+ OnOrOff +".gif'>";
  HTMLReturn = HTMLReturn + "</td>";

  return HTMLReturn;
}
%>
<html>
<head>
<title>Navigator</title>

</head>
<base target="mainFrame">

<body bgcolor="#BFC8DF" text="#000000">

	  <table cellspacing=0 cellpadding=0 width="100%" height="30" border="0"">
        <tr> 
          <td bgcolor="#BFC8DF" height="10">&nbsp;</td>
        </tr>
        <tr> 
          <td>

            <table cellspacing=0 cellpadding=0 width="100%" border=0>
              <tbody> 
              <tr>

                <td valign=top width=128><img src="/OPO5/Organiser_main/Organiser_files/menu.logo.gif" align=top></td>
                <td width=100 noWrap background="/OPO5/Organiser_main/Organiser_files/menu.back.gif">&nbsp;
				</td>

				<%
				 out.println(makeTabs("Diary" , state));
				 out.println(makeTabs("Calendar" , state));
				 out.println(makeTabs("Contacts" , state));
				 out.println(makeTabs("Notes" , state));
				 out.println(makeTabs("Calculator" , state));
				 out.println(makeTabs("Options" , state));
				%>

				<td noWrap align=right width="20"
				background="/OPO5/Organiser_main/Organiser_files/menu.back.gif" bgcolor=#FFFFFF>&nbsp;</td>
			
				<td><img src='/OPO5/Organiser_main/Organiser_files/menu.start.off.gif'></td>
				<td noWrap background='/OPO5/Organiser_main/Organiser_files/menu.back.off.gif'>
				<a href='/OPO5/Logout' target="_parent">
				<b>
				<font face='tahoma,sans-serif' color='#0000CC' size='1'>Logout</font>
				</b>
				</a>&nbsp;
				</td>
				<td><img src='/OPO5/Organiser_main/Organiser_files/menu.end.off.gif'></td>


				<td noWrap align=right width="40"
				background="/OPO5/Organiser_main/Organiser_files/menu.back.gif" bgcolor=#FFFFFF>&nbsp;</td>

				<td noWrap align=right background="/OPO5/Organiser_main/Organiser_files/menu.back.gif" bgcolor=#FFFFFF>
				<a href='/OPO5/Organiser_main/Organiser_files/UserGuide.htm'>
				<b>
				<font face='tahoma,sans-serif' color='#0000CC' size='1'>User Guide</font>
				</b>
				</a>
				</td>

                <td noWrap align=right width="100%" background="/OPO5/Organiser_main/Organiser_files/menu.back.gif" bgcolor=#FFFFFF>&nbsp;</td>

			  </td>
              </tr>
              </tbody> 
            </table>
			</td>
		   </tr>

		  <tr> 
          <td bgcolor="#03369C" height="20">&nbsp;</td>
          </tr>
		  </table>

	</body>
	</html>