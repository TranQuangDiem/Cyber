/**
 * declare sheetObjects
 *
 */
var sheetObjects = new Array();
var sheetCnt = 0;
document.onclick = processButtonClick;
/**
 * 
 * @param sheetObj
 * @param formObj
 * @param sAction
 * @returns {Boolean}
 */
function doActionIBSheet(sheetObj, formObj, sAction) {
	switch (sAction) {
	case IBSEARCH:
		formObj.f_cmd.value = SEARCH;
		/**
		 * Call search page, complete search and return search result data in string.
		 * Unlike DoSearch, this method returns search result data itself without processing search result.
		 * Search result data returned by this method can be loaded to IBSheet if you use them as LoadSearchData parameter.
		 */
		var sXml2 = sheetObj.GetSearchData("Practice2douGS.do",FormQueryString(formObj));
		/**
		 * Get search data (xml or json) as a method parameter and load to IBSheet.
		 * This method can be used to read encrypted search data when security module is used.
		 * Search data are retrieved using GetSearchData method from the server.
		 * The data string fetched is set as a method parameter. The search data are loaded to IBSheet and OnSearchEnd event fires.
		 */
		sheetObj.LoadSearchData(sXml2, {Sync : 1});
		break;
	case IBINSERT:
		with (sheetObj) {
		/**
		 * DataInsert: Create a new data row, and return the row index of the new row
		 * @Syntax: ObjId.DataInsert([Row])
		 * @Param : Row < 0 :		 		Create as the last row
		 * 			Row >= All rows :		Create as the last row
		 * 			Row >= First data row : Create as the first row
		 *			Default :				Create below the selected row
		 */
			console.log()
 			sheetObj.DataInsert(sheetObj.GetSelectRow()==1?-1:sheetObj.GetSelectRow()+1);
 			if ( sheetObj.id == "sheet2" ) {
 				/**
 				 * SearchRows:Check the total row count returned by a search XML
 				 */
 				if( sheetObj.SearchRows()== 0 ){
 					/**
 					 * SetCellValue :Set value in row ,column, new value
 					 * GetCellvalue : get value in row,column
 					 * 
 					 * lastRow:Return the row index of the last row.
 					 * Using this method will return the index of the very last row, not just last data row or the last row as displayed in the screen.
 					 * Note that the last row may be a sum row, data row or even a header row.
 					 * GetSelectRow: currently selected row index
 					 */
 					SetCellValue(LastRow(), "intg_cd_id",sheetObjects[0].GetCellValue(sheetObjects[0].GetSelectRow(),"intg_cd_id"));
 				} else {
 					SetCellValue(LastRow(), "intg_cd_id",sheetObj.GetCellValue(1, "intg_cd_id"));
 				}
 			}
 			return true;
			}
	 		break;
	case "IBSEARCH01":
		formObj.f_cmd.value = SEARCH01;
		/**
		 * Call search page, complete search and return search result data in string.
		 * Unlike DoSearch, this method returns search result data itself without processing search result.
		 * Search result data returned by this method can be loaded to IBSheet if you use them as LoadSearchData parameter.
		 */
		var sXml2 = sheetObj.GetSearchData("Practice2douGS.do",FormQueryString(formObj));
		/**
		 * Call search page, complete search and return search result data in string.
		 * Unlike DoSearch, this method returns search result data itself without processing search result.
		 * Search result data returned by this method can be loaded to IBSheet if you use them as LoadSearchData parameter.
		 */
		sheetObj.LoadSearchData(sXml2, {Sync : 1});
		formObj.Cd_Id.value = "";
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
		var sXml =  sheetObj.DoSave("Practice2douGS.do",FormQueryString(formObj));
		break;
	case "SAVE":
		formObj.f_cmd.value = MULTI01;
		/**
		 * Save data based on data transaction status or column.If Col parameter is not set, data rows whose transaction status is not “Search” is saved.
		 * If there is a particular parameter set in Col, data with values in the designated column will be saved.
		 * If the column is in CheckBox format, only checked boxes will be saved.
		 * If there is no data to save, a warning message will appear and the process is dropped.OnValidation event will fire in the processing collecting data to save.
		 * Depending on the custom logic, failure of OnValidation may result in abortion of saving.
		 * Call the save page using URL and complete saving to read saving XML. Then OnSaveEnd event fires and the whole process completes.
		 * 
		 */
		var sXml =  sheetObj.DoSave("Practice2douGS.do",FormQueryString(formObj));
		break;
	}

}
/**
 * Event fires when saving is completed using saving function and other internal processing has been also completed.
 * If an error message occurs during saving, it will be set as code, a event parameter. Program an error processing logic for any code value smaller than 0.
 * If no result is returned due to network error, send the code value as -3.This event can fire when DoSave or DoAllSave function is called.
 */
function sheet1_OnSaveEnd(sheetObj,code,msg) {
	if(code>=0){
		ComShowCodeMessage('COM132601');
		doActionIBSheet(sheetObjects[0], document.form, IBSEARCH)
	}else {
		ComShowCodeMessage('COM12151','data');
	}
}
/**
 * sheet1_OnDblClick: Event fires when the user double clicks a cell in the data area.
 * @param Row index of the cell
 * @param Column index of the cell
 */
function sheet1_OnDblClick(sheetObj, Row, Col) {	
	var formObj = document.form;
	formObj.Cd_Id.value = sheetObjects[0].GetCellValue(Row, "intg_cd_id");
	doActionIBSheet(sheetObjects[1], formObj, "IBSEARCH01");

}
/**
 * event click button
 */
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
			doActionIBSheet(sheetObjects[0], formObj, IBSEARCH);
			break;
		case "btn_Add":
			doActionIBSheet(sheetObjects[0], formObj, IBINSERT);
			break;
		case "btn_Add2":
			doActionIBSheet(sheetObjects[1], formObj, IBINSERT);
			break;
		case "btn_Save":
			/**
			 * Check the total data row count.
			 * If the Status value is not set, this method will check for the total data row count, including search results and any newly added rows.
			 * Depending on the Status value, you may count the rows with different status, such as Search,Insert, Edit or Deleted.
			 */
			 if((sheetObjects[0].RowCount("I")+sheetObjects[0].RowCount("U")+sheetObjects[0].RowCount("D")) >0 ){
       		  doActionIBSheet(sheetObjects[0],formObj,IBSAVE);
			 }
			 if ((sheetObjects[1].RowCount("I")+sheetObjects[1].RowCount("U")+sheetObjects[1].RowCount("D")) >0 ) {
				 doActionIBSheet(sheetObjects[1],formObj,"SAVE");
			}
//			doActionIBSheet(sheetObjects[0], formObj, IBSAVE);
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
function initSheet(sheetObj, sheetNo) {
	switch (sheetNo) {
	case 1:
		with (sheetObj) {
			var HeadTitle = "STS|Del|SubSystem|Cd Id|Cd Name|Length|Cd Type|Table name|Description Remark|Flag|Create User|Create Date|Update User|Update date";
//			var headCount = ComCountHeadTitle(HeadTitle);
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
			SetConfig({SearchMode : 2,MergeSheet : 5,Page : 40,DataRowMerge : 0});
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
			var headers = [ {Text : HeadTitle,Align : "Center"} ];
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
			 {Type : "Status",		Hidden : 1,Width : 50, Align : "Center",ColMerge : 0,SaveName : "ibflag"},
			 {Type : "DelCheck", 	Hidden : 0,Width : 50, Align : "Center",ColMerge : 0,SaveName : "del_chk" },
			 {Type : "Text",		Hidden : 0,Width : 120,Align : "Center",ColMerge : 0,SaveName : "ownr_sub_sys_cd",	KeyField : 0,Format : "",UpdateEdit : 0,InsertEdit : 1}, 
			 {Type : "Text",		Hidden : 0,Width : 100,Align : "Center",ColMerge : 0,SaveName : "intg_cd_id",		KeyField : 1,Format : "",UpdateEdit : 0,InsertEdit : 1,	AcceptKeys : "E|N", InputCaseSensitive : 1 }, 
			 {Type : "Text",		Hidden : 0,Width : 200,Align : "Center",ColMerge : 0,SaveName : "intg_cd_nm",		KeyField : 0,Format : "",UpdateEdit : 0,InsertEdit : 1,	AcceptKeys : "E|N", InputCaseSensitive : 1 },
			 {Type : "Text",		Hidden : 0,Width : 100,Align : "Center",ColMerge : 0,SaveName : "intg_cd_len",		KeyField : 0,Format : "",UpdateEdit : 1,InsertEdit : 1,	AcceptKeys : "N", EditLen: 2 }, 
			 {Type : "Combo",		Hidden : 0,Width : 100,Align : "Center",ColMerge : 0,SaveName : "intg_cd_tp_cd",	KeyField : 0,Format : "",UpdateEdit : 1,InsertEdit : 1},
			 {Type : "Text",		Hidden : 0,Width : 100,Align : "Center",ColMerge : 0,SaveName : "mng_tbl_nm",		KeyField : 0,Format : "",UpdateEdit : 1,InsertEdit : 1}, 
			 {Type : "Text",		Hidden : 0,Width : 600,Align : "Center",ColMerge : 0,SaveName : "intg_cd_desc",		KeyField : 0,Format : "",UpdateEdit : 1,InsertEdit : 1},
			 {Type : "Combo",		Hidden : 0,Width : 100,Align : "Center",ColMerge : 0,SaveName : "intg_cd_use_flg",	KeyField : 0,Format : "",UpdateEdit : 1,InsertEdit : 1},
			 {Type : "Text",		Hidden : 0,Width : 100,Align : "Center",ColMerge : 0,SaveName : "cre_usr_id",		KeyField : 0,Format : "",UpdateEdit : 1,InsertEdit : 1},
			 {Type : "Date",		Hidden : 0,Width : 100,Align : "Center",ColMerge : 0,SaveName : "cre_dt",			KeyField : 0,Format : "",UpdateEdit : 1,InsertEdit : 1, EditLen: 10}, 
			 {Type : "Text",		Hidden : 0,Width : 100,Align : "Center",ColMerge : 0,SaveName : "upd_usr_id",		KeyField : 0,Format : "",UpdateEdit : 1,InsertEdit : 1}, 
			 {Type : "Date",		Hidden : 0,Width : 100,Align : "Center",ColMerge : 0,SaveName : "upd_dt",			KeyField : 0,Format : "",UpdateEdit : 1,InsertEdit : 1,	EditLen: 10}	            ];
		/**
		 * Check or configure overall editability.If overall editing is not allowed, all cells become uneditable regardless of other settings.
		 */
			SetEditable(1);
		/**
		 * Configure data type, format and functionality of each column.
		 */
			InitColumns(cols);
		/**
		 * SetColProperty :Use this method when you want to define a property of a particular column dynamically, after the property is set in InitColumns Method.
		 * ComboCode : Combo list code group 
		 * ComboText : Combo list text string group
		 */
			SetColProperty("intg_cd_tp_cd", 	{ComboText:"General Code|Table Code", 	ComboCode:"G|T"} );
        	SetColProperty("intg_cd_use_flg", 	{ComboText:"Y|N", 						ComboCode:"Y|N"} );
        /**
         * Check or configure the total height. Set the value in pixel count.
         */
			SetSheetHeight(315);
		}
		break;
	case 2:
		with (sheetObj) {
			var HeadTitle = "STS|Del|Cd Id|Cd Val|Display Name|Description Remark|Order";
			var headCount = ComCountHeadTitle(HeadTitle);
			SetConfig({SearchMode : 2,MergeSheet : 5,Page : 40,DataRowMerge : 0});
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
			var headers = [ {Text : HeadTitle,Align : "Center"} ];
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
			var cols = [ {Type : "Status",		Hidden : 1,Width : 50, Align : "Center",ColMerge : 0,SaveName : "ibflag"},
			             {Type : "DelCheck",	Hidden : 0,Width : 50, Align : "Center",ColMerge : 0,SaveName : "del_chk" }, 
			             {Type : "Text",		Hidden : 0,Width : 100,Align : "Center",ColMerge : 0,SaveName : "intg_cd_id",			KeyField : 1,Format : "",UpdateEdit : 0,InsertEdit : 0}, 
			             {Type : "Text",		Hidden : 0,Width : 100,Align : "Center",ColMerge : 0,SaveName : "intg_cd_val_ctnt",		KeyField : 1,Format : "",UpdateEdit : 0,InsertEdit : 1,	AcceptKeys : "E|N", InputCaseSensitive : 1 },
			             {Type : "Text",		Hidden : 0,Width : 200,Align : "Center",ColMerge : 0,SaveName : "intg_cd_val_dp_desc",	KeyField : 0,Format : "",UpdateEdit : 0,InsertEdit : 1},
			             {Type : "Text",		Hidden : 0,Width : 500,Align : "Center",ColMerge : 0,SaveName : "intg_cd_val_desc",		KeyField : 0,Format : "",UpdateEdit : 1,InsertEdit : 1},
			             {Type : "Text",		Hidden : 0,Width : 100,Align : "Center",ColMerge : 0,SaveName : "intg_cd_val_dp_seq",	KeyField : 0,Format : "",UpdateEdit : 1,InsertEdit : 1}, ];
			/**
			 * Check or configure overall editability.
			 */
			SetEditable(1);
			InitColumns(cols);
			resizeSheet();
		}
		break;
	}

}
/**
 * event when sheet1 changes
 */
function sheet1_OnChange(SheetObj,Row, Col, Value, OldValue,RaiseFlag) {
	with(sheetObjects[0]){
		var cdId = GetCellValue(Row,'intg_cd_id');
		for(var i = 0; i< LastRow(); i++){
			if(cdId==GetCellValue(i,'intg_cd_id')&&i!=Row){
				ComShowCodeMessage("COM12115", "Cd Id");
				SelectCell(Row, 3, true);
				break;
			}
		}
	}
	
}
/**
 * event when sheet changes
 */
function sheet2_OnChange(SheetObj,Row, Col, Value, OldValue,RaiseFlag) {
	with(sheetObjects[1]){
		var msgCd = GetCellValue(Row,'intg_cd_id');
		var cdVal = GetCellValue(Row,'intg_cd_val_ctnt');
		for(var i = 0; i< LastRow(); i++){
			if(msgCd==GetCellValue(i,'intg_cd_id')&&i!=Row&& cdVal==GetCellValue(i,'intg_cd_val_ctnt')){
				ComShowCodeMessage("COM12115", "Cd Id and Cd Val");
				SelectCell(Row, 3, true);
				break;
			}
		}
	}
	
}
/**
 * initializing sheet implementing onLoad event handler in body tag adding
 * first-served functions after loading screen.
 */
function loadPage() {
	for (var i = 0; i < sheetObjects.length; i++) {
		initSheet(sheetObjects[i], i + 1)
	}
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
function setSheetObject(sheet_obj) {
	switch (sheet_obj.id) {
	case "sheet1":
		sheetObjects[0] = sheet_obj;
		break;
	case "sheet2":
		sheetObjects[1] = sheet_obj;
		break;
	default:
		break;
	}
	
}
/**
 * set size sheet
 */
function resizeSheet() {
	ComResizeSheet(sheetObjects[1]);
}
function PRACTICE2() {
	this.processButtonClick = tprocessButtonClick;
	this.setSheetObject = setSheetObject;
	this.loadPage = loadPage;
	this.initSheet = initSheet;
	this.initControl = initControl;
	this.doActionIBSheet = doActionIBSheet;
	this.setTabObject = setTabObject;
	this.validateForm = validateForm;
}