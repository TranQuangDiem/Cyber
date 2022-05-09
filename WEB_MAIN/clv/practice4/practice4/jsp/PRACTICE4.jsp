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
	Exception serverException   = null;			//서버에서 발생한 에러
	String strErrMsg = "";						//에러메세지
	int rowCount	 = 0;						//DB ResultSet 리스트의 건수

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

		// 초기화면 로딩시 서버로부터 가져온 데이터 추출하는 로직추가 ..
		GeneralEventResponse eventResponse = (GeneralEventResponse) request.getAttribute("EventResponse");
		carriers = eventResponse.getETCData("carrier");
		lanes = eventResponse.getETCData("lane");
	}catch(Exception e) {
		out.println(e.toString());
	}
%>
<html>
<head>
<title>Practice4</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script language="javascript">
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
</head>

<body  onLoad="setupPage();">
<form name="form">
<input type="hidden" name="f_cmd">
<input type="hidden" name="pagerows">
<!-- 개발자 작업	-->
<div class="page_title_area clear">
		<h2 class="page_title"><button type="button"><span id="title"></span></button></h2>
		<div class="opus_design_btn">
		   <button type="button" class="btn_accent" name="btn_Retrieve" id="btn_Retrieve">Retrieve</button>
		    <button type="button" class="btn_accent" name="btn_New" id="btn_New">New</button>
		   <button type="button" class="btn_accent" name="btn_Save" id="btn_Save">Save</button>
		   <button type="button" class="btn_accent" name="btn_DownExcel" id="btn_DownExcel">Down Excel</button>
		</div>
	    <div class="location">
	     	<span id="navigation"></span>
	    </div>
	</div>
	<div class="wrap_search">
		<div class="opus_design_inquiry">
			<table>
				<tbody>
					<tr>
						<th style="width: 20px">Carrier <script language="javascript">ComComboObject('carrier');</script></th>
						
						<th style="width: 40px">Vendor <input name="vndr_seq" id="vndr_seq" type="number"></th>
						<th style="float: left;"> Date 
						<input type="text" style="width:62;text-align:center;" name="fr_ym" maxlength="10" dataformat="ymd" placeholder="YYYY-MM-DD" >
						<img src="img/btns_calendar.gif" name="btns_calendar" width="18" height="20" alt="" border="0" align="absmiddle" style="cursor:hand;">
						~<input type="text" style="width:62;text-align:center;"  name="to_ym" maxlength="10" dataformat="ymd" placeholder="YYYY-MM-DD" >
						<img src="img/btns_calendar.gif" name="btns_calendar1" width="18" height="20" alt="" border="0" align="absmiddle" style="cursor:hand;">
						</th>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div class="wrap_result">
	<div class="opus_design_grid">
		<div class="opus_design_btn">
			<button type="button" class="btn_accent" name="btn_Add" id="btn_Add">Row add</button>
		</div>
		<script language="javascript">ComSheetObject('sheet');</script>
	</div>
	</div>


<!-- 개발자 작업  끝 -->
</form>
</body>
</html>