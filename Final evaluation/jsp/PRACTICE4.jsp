<%
/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : PRACTICE4.jsp
*@FileTitle : Practice4
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.05
*@LastModifier : 
*@LastVersion : 1.0
* 2022.05.05 
* 1.0 Creation
=========================================================*/
%>

<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.clt.framework.component.util.JSPUtil"%>
<%@ page import="com.clt.framework.component.util.DateTime"%>
<%@ page import="com.clt.framework.component.message.ErrorHandler"%>
<%@ page import="com.clt.framework.core.layer.event.GeneralEventResponse"%>
<%@ page import="com.clt.framework.support.controller.html.CommonWebKeys"%>
<%@ page import="com.clt.framework.support.view.signon.SignOnUserAccount"%>
<%@ page import="com.clt.apps.opus.esm.clv.practice4.practice4.event.Practice4Event"%>
<%@ page import="org.apache.log4j.Logger" %>

<%
	Practice4Event  event = null;					//PDTO(Data Transfer Object including Parameters)
	Exception serverException   = null;			// error occurred on the server
	String strErrMsg = "";						//error message
	int rowCount	 = 0;						//Number of DB ResultSet list

	String successFlag = "";
	String codeList  = "";
	String pageRows  = "100";

	String strUsr_id		= "";
	String strUsr_nm		= "";
	String carriers			="";
	String lanes			="";
	Logger log = Logger.getLogger("com.clt.apps.Practice4.Practice4");

	try {
	   	SignOnUserAccount account=(SignOnUserAccount)session.getAttribute(CommonWebKeys.SIGN_ON_USER_ACCOUNT);
		strUsr_id =	account.getUsr_id();
		strUsr_nm = account.getUsr_nm();


		event = (Practice4Event)request.getAttribute("Event");
		serverException = (Exception)request.getAttribute(CommonWebKeys.EXCEPTION_OBJECT);

		if (serverException != null) {
			strErrMsg = new ErrorHandler(serverException).loadPopupMessage();
		}

		//Added logic to extract data from server when loading initial screen..
		GeneralEventResponse eventResponse = (GeneralEventResponse) request.getAttribute("EventResponse");
		carriers = eventResponse.getETCData("carrier");
		lanes = eventResponse.getETCData("lane");
	}catch(Exception e) {
		out.println(e.toString());
	}
%>
<script language="javascript">
var userId ="<%=strUsr_nm %>";
var carriers = "<%=carriers%>";
var lanes = "<%=lanes%>";
	function setupPage(){
		var errMessage = "<%=strErrMsg%>";
		if (errMessage.length >= 1) {
			ComShowMessage(errMessage);
		} // end if
		loadPage();
	}
</script>
<form name="form">
<input type="hidden" name="f_cmd">
<input type="hidden" name="pagerows">

	<!-- page_title_area clear(S) -->
	<div class="page_title_area clear">
		<h2 class="page_title"><button type="button"><span id="title"></span></button></h2>
		
		<!-- opus_design_btn(S) -->
		<div class="opus_design_btn"><!-- 
		--><button type="button" class="btn_accent" name="btn_Retrieve" id="btn_Retrieve">Retrieve</button><!-- 
		--><button type="button" class="btn_accent" name="btn_New" id="btn_New">New</button><!-- 
		--><button type="button" class="btn_accent" name="btn_Save" id="btn_Save">Save</button><!-- 
		--><button type="button" class="btn_accent" name="btn_DownExcel" id="btn_DownExcel">Down Excel</button><!--
	 --></div>
		<!-- opus_design_btn(E) -->
		
		<!-- location(S) -->
	    <div class="location">
	     	<span id="navigation"></span>
	    </div>
	    <!-- location(E) -->
	    
	</div>
	<!-- page_title_area clear(E) -->
	
	<!-- wrap_search(S) -->
	<div class="wrap_search">
		<!-- opus_design_inquiry(S) -->
		<div class="opus_design_inquiry wFit">
			<table>
				<colgroup>
					<col width="70" />
					<col width="140" />
					<col width="70" />
					<col width="140" />
					<col width="70" />
					<col width="140" />
					<col width="40" />
					<col width="140" />
					<col width="*" />
				</colgroup>
				<tbody>
					<tr>
						<th>Carrier</th>
						<td><script language="javascript">ComComboObject('carrier');</script></td>
						<th>Vendor</th>
						<td><input name="s_vndr_seq" id="s_vndr_seq" maxlength="6" dataformat="num"></td>
						<th> Date </th>
						<td><input type="text" style="width:62;text-align:center;" name="fr_ym" maxlength="10" dataformat="ymd" placeholder="YYYY-MM-DD" ></td>
						<td><img src="img/btns_calendar.gif" name="btns_calendar" width="18" height="20" alt="" border="0" align="absmiddle" style="cursor:hand;"></td>
						<td><input type="text" style="width:62;text-align:center;"  name="to_ym" maxlength="10" dataformat="ymd" placeholder="YYYY-MM-DD" ></td>
						<td><img src="img/btns_calendar.gif" name="btns_calendar1" width="18" height="20" alt="" border="0" align="absmiddle" style="cursor:hand;"></td>	
					</tr>
				</tbody>
			</table>
		</div>
		<!-- opus_design_inquiry(E) -->
		
	</div>
	<!-- wrap_search(E) -->
	
	<!-- wrap_result(S) -->
	<div class="wrap_result">
	<!-- opus_design_grid(S) -->
		<div class="opus_design_grid">
		
			<!-- opus_design_btn(S) -->
			<div class="opus_design_btn"><!-- 
			 --><button type="button" class="btn_accent" name="btn_Add" id="btn_Add">Row add</button><!--
			 --><button type="button" class="btn_accent" name="btn_Delete" id="btn_Delete">Row delete</button><!--
		 --></div>
			<!-- opus_design_btn(E) -->

			<script language="javascript">ComSheetObject('sheet1');</script>
		</div>
		<!-- opus_design_grid(E) -->
		
	</div>
	<!-- wrap_result(E) -->

</form>