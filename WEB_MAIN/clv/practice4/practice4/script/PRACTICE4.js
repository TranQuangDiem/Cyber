/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : PRACTICE4.js
*@FileTitle : Practice 4
*Open Issues :
*Change history :
*@LastModifyDate : 2022.04.20
*@LastModifier : 
*@LastVersion : 1.0
* 2022.04.20 
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
 * declare list sheet
 */
var sheetObjects = new Array();
var sheetCnt = 0;
/**
 * declare list comboObject
 */
var comboObjects = new Array();
var comboCnt = 0;
/**
 * declare event onclick
 */
document.onclick = processButtonClick;
/**
 * 
 */
var ibsheet =0;


	function doActionIBSheet(sheetObj, formObj, sAction) {
		switch (sAction) {
		case IBSEARCH:
			formObj.f_cmd.value = SEARCH;
			/**
			 * GetSearchData: Call search page, complete search and return search result data in string.
			 * Unlike DoSearch, this method returns search result data itself without processing search result.
			 * Search result data returned by this method can be loaded to IBSheet if you use them as LoadSearchData parameter.
			 */
			var regex = new RegExp("[0-9]{1,6}");
			if(formObj.vndr_seq.value!=''&&!regex.test(formObj.vndr_seq.value)){
				ComShowCodeMessage("COM12125","Vendor Code");
				break;
			}
			var sXml2 =sheetObj.GetSearchData("Practice4GS.do",FormQueryString(formObj));
			/**
			 * LoadSearchData :Get search data (xml or json) as a method parameter and load to IBSheet.
			 * This method can be used to read encrypted search data when security module is used.
			 * Search data are retrieved using GetSearchData method from the server.
			 * The data string fetched is set as a method parameter. The search data are loaded to IBSheet and OnSearchEnd event fires.
			 */
			sheetObj.LoadSearchData(sXml2, {Sync : 1});
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
			sheetObj.DoSave("Practice4GS.do",FormQueryString(formObj));
			break;
		case "IBNEW":
			/**
			 * This function is used to select an item, using a code value.
			 */
			formObj.reset();
			sheetObjects[0].RemoveAll();
//			doActionIBSheet(sheetObj, formObj, IBSEARCH);
			break;
		case IBDELETE:
			/**
			 * currently selected row index
			 */
			var index =sheetObj.GetSelectRow();
//			sheetObj.SetRowHidden(index, 1);
			sheetObj.SetRowStatus(index,"D");
			if(sheetObj.RowCount("D") >0 ){
				doActionIBSheet(sheetObj, formObj, IBSAVE);
			}
			break;
		}

	}
	/**
	 * Event fires when saving is completed using saving function and other internal processing has been also completed.
	 * If an error message occurs during saving, it will be set as code, a event parameter. Program an error processing logic for any code value smaller than 0.
	 * If no result is returned due to network error, send the code value as -3.This event can fire when DoSave or DoAllSave function is called.
	 */
	function sheet1_OnSaveEnd(code,msg) {
		if(Code>=0){
			ComShowCodeMessage("COM132601");
			doActionIBSheet(sheetObjects[0], document.form, IBSEARCH)
		}else {
			ComShowCodeMessage("COM12151","data");
		}
	}
	function processButtonClick() {
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
				if(getCheckConditionPeriod()){
					doActionIBSheet(sheetObjects[0], formObj, IBSEARCH);
				}
				break;
			case "btns_calendar":
    			var vCal=new ComCalendar();
    			vCal.setDisplayType("date");
    			vCal.select(formObj.fr_ym, "yyyy-MM-dd");
    			break;
			case "btns_calendar1":
    			var vCal=new ComCalendar();
    			vCal.setDisplayType("date");
    			vCal.select(formObj.to_ym, "yyyy-MM-dd");
    			break;
			case "btn_Add":
				/**
				 * DataInsert: Create a new data row, and return the row index of the new row
				 * @Syntax: ObjId.DataInsert([Row])
				 * @Param : Row < 0 :		 		Create as the last row
				 * 			Row >= All rows :		Create as the last row
				 * 			Row >= First data row : Create as the first row
				 *			Default :				Create below the selected row
				 */
				sheetObjects[0].DataInsert(-1);
					/**
					 * SetCellValue :Set value in row ,column, new value
					 * GetCellvalue : get value in row,column
					 * 
					 * lastRow:Return the row index of the last row.
					 * Using this method will return the index of the very last row, not just last data row or the last row as displayed in the screen.
					 * Note that the last row may be a sum row, data row or even a header row.
					 * GetSelectRow: currently selected row index
					 * 
					 * ComGetNowInfo: get current date
					 */
				sheetObjects[0].SetCellValue(sheetObjects[0].LastRow(),9,ComGetNowInfo("ymd","/")+" "+ComGetNowInfo("hms"))
				sheetObjects[0].SetCellValue(sheetObjects[0].LastRow(),10,"OPUSADM")
				sheetObjects[0].SetCellValue(sheetObjects[0].LastRow(),11,ComGetNowInfo("ymd","/")+" "+ComGetNowInfo("hms"))
				sheetObjects[0].SetCellValue(sheetObjects[0].LastRow(),12,"OPUSADM")
				break
			case "btn_New":
				doActionIBSheet(sheetObjects[0], formObj, "IBNEW");
				break;
			case "btn_DownExcel":
				if(sheetObjects[0].RowCount() < 1) {// no data
					ComShowCodeMessage("COM132501")
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
					sheetObjects[0].Down2Excel({DownCols: makeHiddenSkipCol(sheetObjects[0]), SheetDesign:1, Merge:1})
				}
				break;
			case "btn_Save":
				doActionIBSheet(sheetObjects[0], formObj, IBSAVE);
				break;
			case "btn_Delete":
				doActionIBSheet(sheetObjects[0], formObj, IBDELETE);
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
	 * check date Invalid
	 */
	function getCheckConditionPeriod(){
        var formObj=document.form;
        var frDt=formObj.fr_ym.value;
        var toDt=formObj.to_ym.value;
        if(frDt==""&&toDt==""){
        	return true;
        }else if(isNaN(ComGetDaysBetween(frDt, toDt))){
        	ComShowCodeMessage('COM12132');
        	 return false;
        }if (ComGetDaysBetween(frDt, toDt) <= 0){
        	ComShowCodeMessage('COM12131');
            return false;
        }
        return true;
    }
	/**
	 * setting sheet initial values and header param : sheetObj, sheetNo adding case
	 * as numbers of counting sheets
	 */
	function initSheet(sheetObj, sheetNo) {
		switch (sheetNo) {
		case 1:
			with (sheetObj) {
				var HeadTitle = "STS|Chk|Carrier|Rev.Lane|Vendor Code|Customer Code|Customer Code|Trade|Delete Flag|Create Date|Create User ID|Update Date|Update User ID";
				var headCount = ComCountHeadTitle(HeadTitle);
				/**
				 *SetConfig: In this method, you may configure how to fetch initialized sheet, location of frozen rows or columns and other basic configurations.
				 *
				 *SearchMode: is where you can configure search mode by selecting one from General, Paging,LazyLoad or real-time server processing modes. The default value is 0.
				 *0:General search mode. Search all data and display all result data on the screen.
				 *1: smClientPaging Paging mode. Search all data and page the result according to Page property configuration.
				 *2: smLazyLoad LazyLoad mode. Search all data and display search result data on the screen by page as set in Page property value according to the scroll location
				 *3: rverPaging Real-time server processing mode Receive only partial search results corresponding to the scroll location from the server and display them on the screen.
				 *   Data not displaying on the screen will be lost.
				 *   
				 *Page: is where you may configure paging unit size for paging mode, LazyLoad mode or real-time server processing mode. The default value is 20.
				 *
				 *MergeSheet: is where you can configure merge styles. The default value is 0
				 *0 msNone No merge is allowed; Default
				 *1 msAll Allow merge for all
				 *2 msPrevColumnMerge Merge is allowed for rows where they are merged in the previous column.
				 *5 msHeaderOnly Allow merge in the header rows only
				 *7 msPrevColumnMerge+ msHeaderOnly Merge is allowed for rows where they are merged in the previous column+ allow merge in the header.
				 *
				 *DataRowMerge can be used to configure whether to allow horizontal merge of the entire row.The default value is 0.
				 */
				SetConfig({SearchMode : 2,MergeSheet : 5,Page : 40,DataRowMerge : 1});
				/**
				 * @param Sort Whether to allow sorting by clicking on the header (Default=1)
				 * @param ColMove Whether to allow column movement in header (Default=1)
				 * @param ColResize Whether to allow resizing of column width (Default=1)
				 * @param HeaderCheck Whether the CheckAll in the header is checked (Default=1)
				 */
				var info = {Sort : 1,ColMove : 1,HeaderCheck : 0,ColResize : 1};
				/**
				 * @param:Text String of texts to display in header,adjoined by "|"
				 * @param:Align String How to align header text (Default = "Center")
				 * 
				 */
				var headers = [ {Text : HeadTitle,Align : "Center"}];
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
				var cols = [ {Type : "Status",Hidden : 1,Width : 50,Align : "Center",ColMerge : 0,SaveName : "ibflag"},
//				 {Type : "DelCheck", Hidden : 1, Width : 50, Align : "Center", ColMerge : 0, SaveName : "del_chk" },
				 {Type : "Text",	Hidden : 0,Width : 120,Align : "Center",ColMerge : 0,SaveName : "chk",			KeyField : 0,Format : "",			UpdateEdit : 0,InsertEdit : 1}, 
				 {Type : "Text",	Hidden : 0,Width : 100,Align : "Center",ColMerge : 0,SaveName : "jo_crr_cd",	KeyField : 1,Format : "",			UpdateEdit : 0,InsertEdit : 1, EditLen:3,	AcceptKeys: "E", 	InputCaseSensitive : 1, FullInput:true}, 
				 {Type : "Combo",	Hidden : 0,Width : 200,Align : "Center",ColMerge : 0,SaveName : "rlane_cd",		KeyField : 1,Format : "",			UpdateEdit : 0,InsertEdit : 1, EditLen:3,}, 
				 {Type : "Text",	Hidden : 0,Width : 100,Align : "Center",ColMerge : 0,SaveName : "vndr_seq",		KeyField : 1,Format : "",			UpdateEdit : 1,InsertEdit : 1, EditLen:6,	AcceptKeys: "N"},
				 {Type : "Text",	Hidden : 0,Width : 100,Align : "Center",ColMerge : 0,SaveName : "cust_cnt_cd",	KeyField : 1,Format : "",			UpdateEdit : 1,InsertEdit : 1, EditLen:2,	AcceptKeys: "E",	InputCaseSensitive : 1, FullInput:true}, 
				 {Type : "Text",	Hidden : 0,Width : 100,Align : "Center",ColMerge : 0,SaveName : "cust_seq",		KeyField : 1,Format : "",			UpdateEdit : 1,InsertEdit : 1, EditLen:6,	AcceptKeys: "N" }, 
				 {Type : "Text",	Hidden : 0,Width : 120,Align : "Center",ColMerge : 0,SaveName : "trd_cd",		KeyField : 0,Format : "",			UpdateEdit : 1,InsertEdit : 1, EditLen:6,	AcceptKeys: "E|N",	InputCaseSensitive : 1},
				 {Type : "Combo",	Hidden : 0,Width : 120,Align : "Center",ColMerge : 0,SaveName : "delt_flg",		KeyField : 0,Format : "",			UpdateEdit : 1,InsertEdit : 1},
				 {Type : "Text",	Hidden : 0,Width : 200,Align : "Center",ColMerge : 0,SaveName : "cre_dt",		KeyField : 0,Format : "",			UpdateEdit : 0,InsertEdit : 0},
				 {Type : "Text",	Hidden : 0,Width : 150,Align : "Center",ColMerge : 0,SaveName : "cre_usr_id",	KeyField : 0,Format : "*********",	UpdateEdit : 0,InsertEdit : 0},
				 {Type : "Text",	Hidden : 0,Width : 200,Align : "Center",ColMerge : 0,SaveName : "upd_dt",		KeyField : 0,Format : "",			UpdateEdit : 0,InsertEdit : 0},
				 {Type : "Text",	Hidden : 0,Width : 100,Align : "Center",ColMerge : 0,SaveName : "upd_usr_id",	KeyField : 0,Format : "*********",	UpdateEdit : 0,InsertEdit : 0}];
				/**
				 * Check or configure overall editability.If overall editing is not allowed, all cells become uneditable regardless of other settings.
				 */
				SetEditable(1);
				/**
				 * Configure data type, format and functionality of each column.
				 */
				InitColumns(cols);
				SetColProperty("rlane_cd", {ComboText:lanes, ComboCode:lanes} );
	        	SetColProperty("delt_flg", {ComboText:"N|Y", ComboCode:"N|Y"} );
				resizeSheet();
			}
			break;
		}

	}
	/**
	 * initializing sheet implementing onLoad event handler in body tag adding
	 * first-served functions after loading screen.
	 */
	function loadPage() {
		for (var i = 0; i < sheetObjects.length; i++) {
			ComConfigSheet(sheetObjects[i]);
			initSheet(sheetObjects[i], i + 1)
			ComEndConfigSheet(sheetObjects[i]);
		}	
		
		for (var k = 0; k < comboObjects.length; k++) {
			initCombo(comboObjects[k], k+1);
		}	
		
		doActionIBSheet(sheetObjects[0],document.form,IBSEARCH)
		
	}
	/**
	 * setting Combo basic info param : comboObj, comboNo initializing sheet
	 */
	function initCombo(comboObj, comboNo) {
		var formObj = document.form
		switch (comboNo) {
		case 1:
			with (comboObj) {
			/**
			 * SetMultiSelect: This function is used to set whether multiple items will be selected or not.
			 * SetDropHeight: set height
			 * InsertItem : Add an item. Set the text value using a “|” separator when using multicolumn.Add to the last row, if the Index parameter is -1.
			 * SetSelectText:  This function is used to select an item, using a text value.
			 */
				comboObj.SetMultiSelect(1);
				comboObj.SetDropHeight(190);
				comboObj.InsertItem(0, "All", "All");
				comboObj.SetSelectText("All", true);
			}
			var carr = carriers.split("|");
			for (var i = 1; i < carr.length; i++) {
				comboObjects[0].InsertItem(i, carr[i], carr[i]);
			}
		}
	}
	
//	function initPeriod(){
//		var formObj = document.form;
//		/**
//		 * ComGetNowInfo: get current date
//		 */
//		var tmpToYm = ComGetNowInfo("ymd","-");
//		/**
//		 * GetDateFormat: format date
//		 */
//		formObj.to_ym.value= tmpToYm;
//		/**
//		 * increase/decrease month
//		 */
//		var tmpFrYm = ComGetDateAdd(formObj.to_ym.value+"-01","D",-1);
//		formObj.fr_ym.value= tmpFrYm;
//	}
	/**
	 * registering IBSheet Object as list adding process for list in case of needing
	 * batch processing with other items defining list on the top of source
	 */
	function setSheetObject(sheet_obj) {
		switch (sheet_obj.id) {
		case "sheet1":
			sheetObjects[0] = sheet_obj;
			break;
		default:
			sheetObjects[sheetCnt++] = sheet_obj;
			break;
		}
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
	 * set size sheet
	 */	
	function resizeSheet() {
		for (var i = 0; i < sheetObjects.length; i++) {
		ComResizeSheet(sheetObjects[i]);
		}
	}
	
    /**
	 * @extends
	 * @class PRACTICE4 : PRACTICE4 생성을 위한 화면에서 사용하는 업무 스크립트를 정의한다.
	 */
    function PRACTICE4() {
    	this.processButtonClick		= tprocessButtonClick;
    	this.setSheetObject 		= setSheetObject;
    	this.loadPage 				= loadPage;
    	this.initSheet 				= initSheet;
    	this.initControl            = initControl;
    	this.doActionIBSheet 		= doActionIBSheet;
    	this.setTabObject 			= setTabObject;
    	this.validateForm 			= validateForm;
    }
    
   	/* 개발자 작업 */


	/* 개발자 작업 끝 */