<%@ taglib uri="http://struts-menu.sf.net/tag" prefix="menu" %>

<table width="100%" border="0" cellpadding="3" cellspacing="3" colspan="3" cellspan="3">
<tr><td>
<menu:useMenuDisplayer name="TabbedMenu" bundle="org.apache.struts.action.MESSAGE">
    <menu:displayMenu name="TabbedHome"/>
    <menu:displayMenu name="TabbedAbout"/>
    <menu:displayMenu name="TabbedContact"/>
    <menu:displayMenu name="TabbedExit"/>
</menu:useMenuDisplayer>
</td></tr>
</table>