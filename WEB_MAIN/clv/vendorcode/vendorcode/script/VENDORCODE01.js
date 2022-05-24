/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : VENDORCODE01.js
*@FileTitle : VendorCode
*Open Issues :
*Change history :
*@LastModifyDate : 2022.05.23
*@LastModifier : 
*@LastVersion : 1.0
* 2022.05.23 
* 1.0 Creation
=========================================================*/
/****************************************************************************************
  이벤트 구분 코드: [초기화]INIT=0; [입력]ADD=1; [조회]SEARCH=2; [리스트조회]SEARCHLIST=3;
					[수정]MODIFY=4; [삭제]REMOVE=5; [리스트삭제]REMOVELIST=6 [다중처리]MULTI=7
					기타 여분의 문자상수  COMMAND01=11; ~ COMMAND20=30;
 ***************************************************************************************/

/*------------------다음 코드는 JSDoc을 잘 만들기 위해서 추가된 코드임 ------------------*/
   /**
     * @fileoverview 업무에서 공통으로 사용하는 자바스크립트파일로 달력 관련 함수가 정의되어 있다.
     * @author 한진해운
     */

    /**
     * @extends 
     * @class VENDORCODE01 : VENDORCODE01 생성을 위한 화면에서 사용하는 업무 스크립트를 정의한다.
     */
    function VENDORCODE01() {
    	this.processButtonClick		= tprocessButtonClick;
    	this.setSheetObject 		= setSheetObject;
    	this.loadPage 				= loadPage;
    	this.initSheet 				= initSheet;
    	this.initControl            = initControl;
    	this.doActionIBSheet 		= doActionIBSheet;
    	this.setTabObject 			= setTabObject;
    	this.validateForm 			= validateForm;
    }
    
    var sheetObjects=new Array();
    var sheetCnt=0;
    var selectVal;
    var iPageNo = 1;
    document.onclick=processButtonClick; 
    	function processButtonClick(){
    		var sheetObject=sheetObjects[0];
    		/*******************************************************/
    		var formObject=document.form;
    		try {
    			var srcName=ComGetEvent("name");
    			if(ComGetBtnDisable(srcName)) return false;
    			switch(srcName) {
                	case "btn_Retrieve":
                		doActionIBSheet(sheetObject,formObject,IBSEARCH);
                		break;
                	case "btn_Close":
                		ComClosePopup(); 
                		break;
                	case "btn_OK":
                		comPopupOK();
                		break;
                	case "btn2_Down_Excel":
                		if(sheetObj.RowCount() < 1){//no data
                			ComShowCodeMessage("COM132501");
                			}else{
                				sheetObject.Down2Excel({ DownCols: makeHiddenSkipCol(sheetObject),HiddenColumn:false,Merge:false,TreeLevel:false});
                			}
            	        break;  
    			} // end switch
    		}catch(e) {            
    			if( e == "[object Error]") {
    				ComShowMessage(OBJECT_ERROR);
    			} else {
    				ComShowMessage(e.message);
    			}
    		}
    	}
        function setSheetObject(sheet_obj){
            sheetObjects[sheetCnt++]=sheet_obj;
        }
        function loadPage() {
            for(i=0;i<sheetObjects.length;i++){
                ComConfigSheet(sheetObjects[i]);
                initSheet(sheetObjects[i],i+1);
                ComEndConfigSheet(sheetObjects[i]);
                doActionIBSheet(sheetObjects[i],document.form,IBSEARCH)
            }
    	}
         function initSheet(sheetObj,sheetNo) {
             var cnt=0;
             //sheetObj.UseUtf8 = true;
             switch(sheetNo) {
                 case 1:      //IBSheet1 init
                     with(sheetObj){
		                  var HeadTitle="||Code|Name|Legacy Code|Country|Location|Control Office| Status| Scac" ;
		                  SetConfig( { SearchMode:2, MergeSheet:0, Page:200, DataRowMerge:0 } );
		                  var info    = { Sort:1, ColMove:1, HeaderCheck:1, ColResize:1 };
		                  var headers = [ { Text:HeadTitle, Align:"Center"} ];
		                  InitHeaders(headers, info);
		                  var cols = [ {Type:"Radio",     Hidden:0, Width:20,   Align:"Center",  ColMerge:0,   SaveName:"radio",     KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
		                               {Type:"CheckBox",  Hidden:0, Width:30,   Align:"Center",  ColMerge:0,   SaveName:"checkbox",         KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:1,   InsertEdit:1 },
		                      {Type:"Text",      Hidden:0,  Width:70,   Align:"Center",  ColMerge:0,   SaveName:"code",      KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:1 },
		                      {Type:"Text",      Hidden:0,  Width:230,  Align:"Left",    ColMerge:0,   SaveName:"name",      KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:1 },
		                      {Type:"Text",      Hidden:0,  Width:120,   Align:"Center",  ColMerge:0,   SaveName:"scac",   KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:1 },
		                      {Type:"Text",      Hidden:0,  Width:60,   Align:"Center",  ColMerge:0,   SaveName:"country",   KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:1 },
		                      {Type:"Text",      Hidden:0,  Width:70,   Align:"Center",  ColMerge:0,   SaveName:"location",  KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:1 },
		                      {Type:"Text",      Hidden:0,  Width:100,  Align:"Center",  ColMerge:0,   SaveName:"office",    KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:1 },
		                  	  {Type:"Text",      Hidden:0,  Width:70,   Align:"Center",  ColMerge:0,   SaveName:"status",    KeyField:0,   CalcLogic:"",   Format:"",            PointCount:0,   UpdateEdit:0,   InsertEdit:1 } ];
                           InitColumns(cols);
                           SetEditable(1);
                           resizeSheet();
                  	}
                 break;
             }
         }
         function resizeSheet() {
     		for (var i = 0; i < sheetObjects.length; i++) {
     		ComResizeSheet(sheetObjects[i]);
     		}
     	}
         function doActionIBSheet(sheetObj,formObj,sAction) {
             sheetObj.ShowDebugMsg(false);
             switch(sAction) {
               case IBSEARCH:
                     formObj.f_cmd.value=SEARCH;
                     sheetObj.DoSearch("VendorCode01GS.do", FormQueryString(formObj) );
                     break;
             }
         }    
         function setgetUpper(obj) {
         	return obj.value=obj.value.toUpperCase();
         }
	/* 개발자 작업  끝 */