<script language="JavaScript" type="text/javascript">
    // Dojo configuration
    djConfig = {
        baseRelativePath: "<@s.url includeParams='none' value='/struts/dojo' includeParams="none" encode='false'/>",
        isDebug: ${parameters.debug?default(false)},
        bindEncoding: "${parameters.encoding}",
        debugAtAllCosts: ${parameters.debug?default(false)}, // fix for firefox 3
    };
</script>
<script language="JavaScript" type="text/javascript"
        src="<@s.url includeParams='none' value='/struts/dojo/dojo.js' includeParams="none" encode='false'/>"></script>
<script language="JavaScript" type="text/javascript"
        src="<@s.url includeParams='none' value='/struts/simple/dojoRequire.js' includeParams="none" encode='false'/>"></script>