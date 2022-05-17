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
<script language="javascript">
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
<!-- 개발자 작업	-->

	<!-- page_title_area clear(S) -->
	<div class="page_title_area clear">
		<!-- page_title=(S) -->
		<h2 class="page_title"><button type="button"><span id="title"></span></button></h2>
		<!-- page_title_area clear(E) -->
		
		<!-- opus_design_btn(S) -->
		<div class="opus_design_btn"><!-- 
		    --><button type="button" class="btn_accent" name="btn_Retrieve" id="btn_Retrieve">Retrieve</button><!-- 
			--><button type="button" class="btn_normal" name="btn_Save" id="btn_Save">Save</button><!-- 
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
					<col width="*" />
				</colgroup>
				<tbody>
					<tr>
						<th>Subsystem</th>
						<td><input style="width: 100px" name="subSystem" id="subSystem" type="text"></td>
						<th>Cd Id</th>
						<td><input style="width: 100px" name="Cd_Id" id="Cd_Id" type="text"></td>
					</tr>
				</tbody>
			</table>
		</div>
		<!-- opus_design_inquiry(S) -->
		
	</div>
	<!-- wrap_search(E) -->
	
	<!-- wrap_result(S) -->
	<div class="wrap_result">
		
		<!-- opus_design_grid(S) -->
		<div class="opus_design_grid">
			<h3 class="title_design">Master</h3>
			
			<!-- opus_design_btn(S) -->
			<div class="opus_design_btn"><!--
				--><button type="button" class="btn_accent" name="btn_Add" id="btn_Add">Row add</button><!--
		--></div>
			<!-- opus_design_btn(E) -->
			
			<script language="javascript">ComSheetObject('sheet1');</script>
		</div>
		<!-- opus_design_grid(E) -->
		
	</div>
	<!-- wrap_result(E) -->
	
	<!-- wrap_result(S) -->
	<div class="wrap_result">
	
		<!-- opus_design_grid(S) -->
		<div class="opus_design_grid">
			<h3 class="title_design">Detail</h3>
			
			<!-- opus_design_btn(S) -->
			<div class="opus_design_btn"><!--
				--><button type="button" class="btn_accent" name="btn_Add2" id="btn_Add2">Row add</button><!--
		--></div>
			<!-- opus_design_btn(E) -->
			
			<script language="javascript">ComSheetObject('sheet2');</script>
		</div>	
		<!-- opus_design_grid(E) -->
		
	</div>

<!-- 개발자 작업  끝 -->
</form>