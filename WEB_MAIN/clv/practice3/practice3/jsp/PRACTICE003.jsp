<%
/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : PRACTICE003.jsp
*@FileTitle : Practice 3
*Open Issues :
*Change history :
*@LastModifyDate : 2022.04.20
*@LastModifier : 
*@LastVersion : 1.0
* 2022.04.20 
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
<%@ page import="com.clt.apps.opus.esm.clv.practice3.practice3.event.Practice003Event"%>
<%@ page import="org.apache.log4j.Logger" %>

<%
	Practice003Event  event = null;					//PDTO(Data Transfer Object including Parameters)
	Exception serverException   = null;			//서버에서 발생한 에러
	String strErrMsg = "";						//에러메세지
	int rowCount	 = 0;						//DB ResultSet 리스트의 건수

	String successFlag = "";
	String codeList  = "";
	String pageRows  = "100";

	String strUsr_id		= "";
	String strUsr_nm		= "";
	String partner1 = "";
	Logger log = Logger.getLogger("com.clt.apps.Practice3.Practice3");

	try {
	   	SignOnUserAccount account=(SignOnUserAccount)session.getAttribute(CommonWebKeys.SIGN_ON_USER_ACCOUNT);
		strUsr_id =	account.getUsr_id();
		strUsr_nm = account.getUsr_nm();


		event = (Practice003Event)request.getAttribute("Event");
		serverException = (Exception)request.getAttribute(CommonWebKeys.EXCEPTION_OBJECT);

		if (serverException != null) {
			strErrMsg = new ErrorHandler(serverException).loadPopupMessage();
		}

		// 초기화면 로딩시 서버로부터 가져온 데이터 추출하는 로직추가 ..
		GeneralEventResponse eventResponse = (GeneralEventResponse) request.getAttribute("EventResponse");
		partner1 = eventResponse.getETCData("partner");

	}catch(Exception e) {
		out.println(e.toString());
	}
%>
<html>
<head>
<title>Practice 3</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script language="javascript">
var partners = "<%=partner1%>"
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
			
	    <div class="location">
	     	<span id="navigation"></span>
	    </div>
	</div>
	<div class="wrap_search">
		<div class="opus_design_btn">
				
				<table>
					<tbody>
						<tr>
							<th>Year Month</th>
							<th style="width: 92px;">
									<input type="text" style="width:80px;" class="input1" dataformat="ym" maxlength="7" name="fr_yrmon" value="" id="fr_yrmon" cofield="btn_from_back"/> 
							</th>
							<th style="width: 52px;"><button type="button" class="btn_left" name="btn_from_back" id="btn_from_back"></button> 
								<button type="button" class="btn_right" name="btn_from_next" id="btn_from_next"></button>
							</th>
							<th style="width: 102px;">
								<input type="text" style="width:80px;" class="input1" maxlength="7" dataformat="ym"  name="to_yrmon" value="" id="to_yrmon" cofield="btn_to_next" />
							</th>
							<th style="width: 52px;"><button type="button" class="btn_left" name="btn_to_back" id="btn_to_back"></button> 
								<button type="button" class="btn_right" name="btn_to_next" id="btn_to_next"></button>
							</th>
							
							<th> Partner<script language="javascript">ComComboObject('partner');</script></th>
							<th>Lane <script language="javascript">ComComboObject('lane');</script></th>
							<th>Trade <script language="javascript">ComComboObject('trade');</script></th>
							<th><button type="button" class="btn_accent" name="btn_Retrieve" id="btn_Retrieve">Retrieve</button>
								<button type="button" class="btn_accent" name="btn_New" id="btn_New">New</button>
			   					<button type="button" class="btn_accent" name="btn_DownExcel" id="btn_DownExcel">Down Excel</button>
			    				<button type="button" class="btn_accent" name="btn_DownExcel2" id="btn_DownExcel2">Down</button>
							</th>
						</tr>
					</tbody>
				</table>
		    </div>
	</div>
	<div class="wrap_result">
		<div class= "opus_design_tab" style="width: 100%;margin: 0 0 3px">
			<script language="javascript">ComTabObject('tab1')</script>
		</div>
		<div class="opus_design_grid clear" name="tabLayer" id="tabLayer">
			<script language="javascript">ComSheetObject('sheet1');</script>		
		</div>
		<div class="opus_design_grid clear" name="tabLayer" id="tabLayer">
			<script language="javascript">ComSheetObject('sheet2');</script>		
		</div>
	</div>		



<!-- 개발자 작업  끝 -->
</form>
</body>
</html>