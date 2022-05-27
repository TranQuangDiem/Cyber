/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : PRACTICE_TRN.js
*@FileTitle : ErrorMessageManager
*Open Issues :
*Change history :
*@LastModifyDate : 2022.04.07
*@LastModifier : 
*@LastVersion : 1.0
* 2022.04.07 
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
     * @class PRACTICE_TRN : PRACTICE_TRN 생성을 위한 화면에서 사용하는 업무 스크립트를 정의한다.
     */
/**
 * declare list sheet
 */	    
var sheetObjects=new Array();
var sheetCnt=0;
/**
 * declare event onclick
 */
document.onclick=processButtonClick;
   	/* 개발자 작업	*/
/**
 * perform ibsheet related events : search,save
 * @param sheetObj : sheetObject
 * @param formObj : document.form
 * @param sAction : IBSEARCH, IBSAVE, IBDELETE,....
 * 
 */
function doActionIBSheet(sheetObj,formObj,sAction) {
	sheetObj.ShowDebugMsg(false);
	switch (sAction) {
	case IBSEARCH:
		formObj.f_cmd.value = SEARCH;
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
		sheetObj.DoSearch("Practice_TRNGS.do", FormQueryString(formObj) );
		break;
	case IBINSERT:
		/**
		 * DataInsert: Create a new data row, and return the row index of the new row
		 * @Syntax: ObjId.DataInsert([Row])
		 * @Param : Row < 0 :		 		Create as the last row
		 * 			Row >= All rows :		Create as the last row
		 * 			Row >= First data row : Create as the first row
		 *			Default :				Create below the selected row
		 */
			sheetObj.DataInsert(-1);
		break;
	case IBSAVE:
		formObj.f_cmd.value = MULTI;
		/**
		 * Save data based on data transaction status or column.If Col parameter is not set, data rows whose transaction status is not “Search” is saved.
		 * If there is a particular parameter set in Col, data with values in the designated column will be saved.
		 * If the column is in CheckBox format, only checked boxes will be saved.
		 * If there is no data to save, a warning message will appear and the process is dropped.OnValidation event will fire in the processing collecting data to save.
		 * Depending on the custom logic, failure of OnValidation may result in abortion of saving.
		 * Call the save page using URL and complete saving to read saving XML. Then OnSaveEnd event fires and the whole process completes.
		 * 
		 */
		sheetObj.DoSave("Practice_TRNGS.do", FormQueryString(formObj));
		break;
	}
}
/**
 * Event fires when saving is completed using saving function and other internal processing has been also completed.
 * If an error message occurs during saving, it will be set as code, a event parameter. Program an error processing logic for any code value smaller than 0.
 * If no result is returned due to network error, send the code value as -3.This event can fire when DoSave or DoAllSave function is called.
 */
function sheet1_OnSaveEnd(SheetObj,Code,Msg,StCode,StMsg) {
	if(Code>=0){
		ComShowCodeMessage('COM132601');
		doActionIBSheet(sheetObjects[0], document.form, IBSEARCH)
	}else {
		ComShowCodeMessage('COM12151','data');
	}
}
/**
 * event click button
 */
function processButtonClick() {
	/** *** setting sheet object **** */
	var sheetObject1 = sheetObjects[0];
	/** **************************************************** */
	var formObj = document.form;
	try {
		/**
		 * get the name of the tag clicked
		 */
		var srcName = ComGetEvent("name");
		if (srcName == null) {
			return;
		}
		switch (srcName) {
			case "btn_Retrieve":
				doActionIBSheet(sheetObject1, formObj, IBSEARCH);
				break;
			case "btn_DownExcel":
				if (sheetObject1.RowCount() < 1) {// no data
					ComShowCodeMessage("COM132501");
				} else {
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
					sheetObject1.Down2Excel({DownCols: makeHiddenSkipCol(sheetObject1), SheetDesign:1, Merge:1});
				}
				break;
			case "btn_Add":
				doActionIBSheet(sheetObject1, formObj, IBINSERT)
				break;
			case "btn_Save":
//				if(validateForm(sheetObject1)){
				doActionIBSheet(sheetObject1,formObj,IBSAVE);
//				}
				break;
			}
	} catch (e) {
		if (e == "[object Error]") {
			ComShowCodeMessage('JOO00001');
		} else {
			ComShowMessage(e.message);
		}
	}
}
/**
 * setting sheet initial values and header param : sheetObj, sheetNo adding case
 * as numbers of counting sheets
 */
function initSheet(sheetObj,sheetNo) {
	var cnt = 0;
	switch (sheetNo) {
	case 1: // sheet1 init
		with (sheetObj) {
		
			var HeadTitle = "STS|Del|Msg Cd|Msg Type|Msg Level|Message|Description";
//			var headCount = ComCountHeadTitle(HeadTitle);
			SetConfig({SearchMode : 2, MergeSheet : 5, Page : 40, DataRowMerge : 0});
		/**
		 * @param Sort Whether to allow sorting by clicking on the header (Default=1)
		 * @param ColMove Whether to allow column movement in header (Default=1)
		 * @param ColResize Whether to allow resizing of column width (Default=1)
		 * @param HeaderCheck Whether the CheckAll in the header is checked (Default=1)
		 */
			var info = {Sort : 1, ColMove : 1, HeaderCheck : 0, ColResize : 1};
		/**
		 * @param:Text String of texts to display in header,adjoined by "|"
		 * @param:Align String How to align header text (Default = "Center")
		 * 
		 */
			var headers = [ { Text : HeadTitle, Align : "Center" }];
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
			var cols = [ 
	             { Type : "Status", 	Hidden : 1, Width : 50,  Align : "Center", ColMerge : 0, SaveName : "ibflag" }, 
	             { Type : "DelCheck",	Hidden : 0, Width : 50,  Align : "Center", ColMerge : 0, SaveName : "del_chk" }, 
	             { Type : "Text", 		Hidden : 0, Width : 100, Align : "Center", ColMerge : 0, SaveName : "err_msg_cd", 	KeyField : 1, UpdateEdit : 0, InsertEdit : 1, 	EditLen: 8 , AcceptKeys : "E|N", 	InputCaseSensitive : 1 }, 
	             { Type : "Text", 		Hidden : 0, Width : 100, Align : "Center", ColMerge : 0, SaveName : "err_tp_cd",  	KeyField : 1, UpdateEdit : 0, InsertEdit : 1, 	EditLen: 1 , AcceptKeys : "E", 		InputCaseSensitive : 1 }, 
	             { Type : "Text", 		Hidden : 0, Width : 100, Align : "Center", ColMerge : 0, SaveName : "err_lvl_cd", 	KeyField : 1, UpdateEdit : 1, InsertEdit : 1, 	EditLen: 1 , AcceptKeys : "E", 		InputCaseSensitive : 1 }, 
	             { Type : "Text", 		Hidden : 0, Width : 600, Align : "Left",   ColMerge : 0, SaveName : "err_msg", 		KeyField : 1, UpdateEdit : 1, InsertEdit : 1 }, 
	             { Type : "Text", 		Hidden : 0, Width : 100, Align : "Left",   ColMerge : 0, SaveName : "err_desc", 	KeyField : 1, UpdateEdit : 1, InsertEdit : 1 } 
	             ];
			/**
			 * Configure data type, format and functionality of each column.
			 */
			InitColumns(cols);
			SetEditable(1);
			SetWaitImageVisible(0);
			resizeSheet();
		}
		break;		
	}
}
/**
 * event when sheet changes
 */
function sheet1_OnChange(SheetObj,Row, Col, Value, OldValue,RaiseFlag) {
	with(sheetObjects[0]){
		var msgCd = GetCellValue(Row,'err_msg_cd');
		for(var i = 0; i< LastRow(); i++){
			if(msgCd==GetCellValue(i,'err_msg_cd')&&i!=Row){
				ComShowCodeMessage("COM12115", "Msg Cd");
				SelectCell(Row, 2, true);
				SetCellText(Row, 2, "" );
				break;
			}
		}
	}
	
}
/**
 * initializing sheet implementing onLoad event handler in body tag adding
 * first-served functions after loading screen.
 */
function loadPage(){
	//generate Grid Layout
	for (i = 0; i < sheetObjects.length; i++) {
		ComConfigSheet(sheetObjects[i]);
		initSheet(sheetObjects[i], i + 1);
		ComEndConfigSheet(sheetObjects[i]);
	}
	
	//auto search data after loading finish.
	doActionIBSheet(sheetObjects[0], document.form, IBSEARCH);
	initControl();
}
function initControl(){
	var form = document.form;
	axon_event.addListenerFormat ('keydown', 'ComEditFormating', form);
	axon_event.addListener ('keydown', 'ComKeyEnter', 'form');
}
/**
 * registering IBSheet Object as list adding process for list in case of needing
 * batch processing with other items defining list on the top of source
 */
function setSheetObject(sheet_obj){
	switch (sheet_obj.id) {
	case "sheet1":
		sheetObjects[0] = sheet_obj;
		break;
	default:
		break;
	}
	
}
/**
 * set size sheet
 */
function resizeSheet() {
	ComResizeSheet(sheetObjects[0]);
}

/**
 * registering IBCombo Object as list param : combo_obj adding process for list
 * in case of needing batch processing with other items defining list on the top
 * of source
 */
function setComboObject(combo_obj) {
	comboObjects[comboCnt++] = combo_obj;
}
/**
 * validation errMsgCd
 */
function validateForm(sheetObj){
	var regex = new RegExp("^[A-Z]{3}\\d{5}");
	for( var i = sheetObj.LastRow(); i >= sheetObj.HeaderRows(); i-- ) {
		if(sheetObj.GetCellValue(i, "ibflag") == 'I' &&!regex.test(sheetObj.GetCellValue(i, "err_msg_cd"))){
				ComShowMessage('errMsgCD 8 characters are required, the first 3 characters are uppercase letters, the last 5 characters are numbers and cannot be the same as the existing Message Code');
				return false;
			}
		}
	return true;
}
    function DOU_TRN_001() {
    	this.processButtonClick		= tprocessButtonClick;
    	this.setSheetObject 		= setSheetObject;
    	this.loadPage 				= loadPage;
    	this.initSheet 				= initSheet;
    	this.initControl            = initControl;
    	this.doActionIBSheet 		= doActionIBSheet;
    	this.setTabObject 			= setTabObject;
    	this.validateForm 			= validateForm;
    }
    

	/* 개발자 작업  끝 */