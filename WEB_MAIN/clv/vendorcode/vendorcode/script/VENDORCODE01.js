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
                		/**
                		 * close popup
                		 */
                		ComClosePopup(); 
                		break;
                	case "btn_OK":
                		comPopupOK();
                		break;
                	case "btn2_Down_Excel":
                		if(sheetObj.RowCount() < 1){//no data
                			ComShowCodeMessage("COM132501");
                			}else{
                				/**IF there are any search result data returned, download the data displayed in IBSheet into an excel file.
            					 * @param: DownCols parameter is a string connecting all downloading columns using "|". You can use either SaveName or column index.
            					 * If this is null, all columns are downloaded.
            					 * @param :FileName parameter is used to set the downloaded excel file name. If file extension is set as xls,excel 2003 format file is downloaded.
            					 * If xlsx, excel 2007 format file is downloaded. If no value is set, an xls file is downloaded.
            					 * @param:SheetDesign parameter reflects IBSheet design. Font name, font size and background color are available.
            					 * Using this parameter may result in further delay of performance. Default is 0 (Not applydesign). Font color will always display in black.
            					 * Multiple font coloring is not supported. For cell background color, up to 48 colors may be used concurrently for a single excel file.
            					 * If one IBSheet includes a larger number of colors, some of the colors will display to the closest color among the 48 available colors.
            					 * If this parameter is set as 2, design will be still reflected as in 1,but cell borders will not be lined.
            					 * @param: Merge parameter determines whether to apply IBSheet merge status to excel document.
            					 * Using this parameter may result in further delay of performance. Default is 0 (not use merging).
            					 */
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
        /**
         * initializing sheet implementing onLoad event handler in body tag adding
         * first-served functions after loading screen.
         */
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
		                /**
		          		 * @param Sort Whether to allow sorting by clicking on the header (Default=1)
		          		 * @param ColMove Whether to allow column movement in header (Default=1)
		          		 * @param ColResize Whether to allow resizing of column width (Default=1)
		          		 * @param HeaderCheck Whether the CheckAll in the header is checked (Default=1)
		          		 */
		                  var info    = { Sort:1, ColMove:1, HeaderCheck:1, ColResize:1 };
		                  /**
		          		 * @param:Text String of texts to display in header,adjoined by "|"
		          		 * @param:Align String How to align header text (Default = "Center")
		          		 * 
		          		 */
		                  var headers = [ { Text:HeadTitle, Align:"Center"} ];
		                  /**
		          		 * You can define the header title and function using this method.
		          		 */
		                  InitHeaders(headers, info);
		                  
		                  /**
		          		 * @param Type Column data type (Required)
		          		 * @param Hidden can be used to configure whether to hide a certain data column.
		          		 * @param Width Column width
		          		 * @param Align Data alignment
		          		 * @param ColMerge configures whether to allow vertical merge for data columns. The default value is 1.
		          		 * @param SaveName can be used to configure the parameter names to use when saving data. If not configured, names will be given sequentially as in C1, C2 and so on.
		          		 * @param KeyField can be used to configure whether to make a data cell a required field
		          		 *  If the value is set as 1 and the data cell is empty, a warning message appears when the saving method is called so as to encourage the user to fill the cell.
		          		 *  The default value is 0.
		          		 * @param UpdateEdit Whether to allow data editing when transaction is in "Search" state
		          		 * @param InsertEdit can be used to configure editability of data the transaction status of which is Insert.The default value is 1.
		          		 * @param EditLen can be used to configure the maximum number of characters to allow for a piece of data.
		          		 */
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
        /**
     	 * registering IBSheet Object as list adding process for list in case of needing
     	 * batch processing with other items defining list on the top of source
     	 */
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
                     /**
             		 * DoSearch : Connect to search page to read search XML, and then load XML data internally in IBSheet
             		 * @Syntax: ObjId.DoSearch(PageUrl, [Param], [Opt])
             		 * @Param: parameter can be set by connecting conditions using "=" and "&", as in “Condition name=value 1&condition name 2=value 2”.
             		 *In Opt parameter, an object-type parameter, you can set whether to do Sync search (Sync) and Append search (Append).
             		 *Sync parameter is sync/async search mode. Async search means when there are multiple calls
             		 *sent, following calls for search will be ignored if the first search is not complete. If you need to run multiple calls and all searches must be complete,
             		 * use sync mode.
             		 *If you set Append parameter as 1, you can append the existing data to the current search data to run search.
             		 *Call the search page using URL and complete data representation by reading search data. Then OnSearchEnd event fires and the whole process completes.
             		 */
                     sheetObj.DoSearch("VendorCode01GS.do", FormQueryString(formObj) );
                     break;
             }
         }    
//         function setgetUpper(obj) {
//         	return obj.value=obj.value.toUpperCase();
//         }