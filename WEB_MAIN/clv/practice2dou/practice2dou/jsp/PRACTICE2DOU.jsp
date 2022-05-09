<%@page import="com.clt.apps.opus.esm.clv.pratice2dou.pratice2dou.event.Practice2Event"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.clt.framework.component.util.JSPUtil"%>
<%@ page import="com.clt.framework.component.util.DateTime"%>
<%@ page import="com.clt.framework.component.message.ErrorHandler"%>
<%@ page import="com.clt.framework.core.layer.event.GeneralEventResponse"%>
<%@ page import="com.clt.framework.support.controller.html.CommonWebKeys"%>
<%@ page import="com.clt.framework.support.view.signon.SignOnUserAccount"%>
<%@ page import="org.apache.log4j.Logger" %>

<%
	Practice2Event  event = null;					//PDTO(Data Transfer Object including Parameters)
	Exception serverException   = null;			//서버에서 발생한 에러
	String strErrMsg = "";						//에러메세지
	int rowCount	 = 0;						//DB ResultSet 리스트의 건수

	String successFlag = "";
	String codeList  = "";
	String pageRows  = "100";

	String strUsr_id		= "";
	String strUsr_nm		= "";
	Logger log = Logger.getLogger("com.clt.apps.Practice2dou.Practice2dou");

	try {
	   	SignOnUserAccount account=(SignOnUserAccount)session.getAttribute(CommonWebKeys.SIGN_ON_USER_ACCOUNT);
		strUsr_id =	account.getUsr_id();
		strUsr_nm = account.getUsr_nm();


		event = (Practice2Event)request.getAttribute("Event");
		serverException = (Exception)request.getAttribute(CommonWebKeys.EXCEPTION_OBJECT);

		if (serverException != null) {
			strErrMsg = new ErrorHandler(serverException).loadPopupMessage();
		}

		// 초기화면 로딩시 서버로부터 가져온 데이터 추출하는 로직추가 ..
		GeneralEventResponse eventResponse = (GeneralEventResponse) request.getAttribute("EventResponse");

	}catch(Exception e) {
		out.println(e.toString());
	}
%>
<html>
<head>
<title>Practice 2</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script language="javascript">
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
			<button type="button" class="btn_normal" name="btn_Save" id="btn_Save">Save</button>
		   <!--<button type="button" class="btn_normal" name="btn_DownExcel" id="btn_DownExcel">Down Excel</button> --> 
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
						<th style="width: 200px">Subsystem
							<input name="subSystem" id="subSystem" type="text">
						</th>
						<th style="width: 40px">Cd Id
						</th>
						<td> <input name="Cd_Id" id="Cd_Id" type="text"></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div class="wrap_result">
		<div class="opus_design_grid">
			<h3 class="title_design">Master</h3>
			<div class="opus_design_btn">
				<button type="button" class="btn_accent" name="btn_Add" id="btn_Add">Row add</button>
				<!-- <button type="button" class="btn_accent" name="btn_Delete" id="btn_Delete">Row delete</button> -->
			</div>
			<script language="javascript">ComSheetObject('sheet1');</script>
		</div>
	</div>
	<div class="wrap_result">
		<div class="opus_design_grid">
			<h3 class="title_design">Detail</h3>
			<div class="opus_design_btn">
				<button type="button" class="btn_accent" name="btn_Add2" id="btn_Add2">Row add</button>
				<!-- <button type="button" class="btn_normal" name="btn_Save2" id="btn_Save2">Save</button> -->
			</div>
			<script language="javascript">ComSheetObject('sheet2');</script>
		</div>	
	</div>

<!-- 개발자 작업  끝 -->
</form>
</body>
</html>