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
			var sXml2 = sheetObj.DoSave("Practice4GS.do",FormQueryString(formObj));
			break;
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
				sheetObjects[0].DataInsert(-1);
				sheetObjects[0].SetCellValue(sheetObjects[0].LastRow(),9,ComGetNowInfo("ymd","/")+" "+ComGetNowInfo("hms"))
				sheetObjects[0].SetCellValue(sheetObjects[0].LastRow(),10,"OPUSADM")
				sheetObjects[0].SetCellValue(sheetObjects[0].LastRow(),11,ComGetNowInfo("ymd","/")+" "+ComGetNowInfo("hms"))
				sheetObjects[0].SetCellValue(sheetObjects[0].LastRow(),12,"OPUSADM")
				break
			case "btn_New":
				comboObjects[0].SetSelectCode( -1, true);
				comboObjects[0].SetSelectCode( "All", true);
				formObj.fr_ym.value='';
				formObj.to_ym.value='';
				formObj.vndr_seq.value='';
				doActionIBSheet(sheetObjects[0], formObj, IBSEARCH);
				break;
			case "btn_DownExcel":
				sheetObjects[0].Down2Excel({DownCols: makeHiddenSkipCol(sheetObjects[0]), SheetDesign:1, Merge:1})
				break;
			case "btn_Save":
				doActionIBSheet(sheetObjects[0], formObj, IBSAVE);
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
	function getCheckConditionPeriod(){
//		var regex = new RegExp("^[0-9]{4}-[0-1][0-9]");
//		if(!regex.test(formObj.fr_yrmon.value)||!regex.test(formObj.to_yrmon.value)){
//			alert('year month illegal');	
//            return false;
//		}
        var formObj=document.form;
        var frDt=formObj.fr_ym.value;
        var toDt=formObj.to_ym.value;
//        console.log(frDt+"----"+toDt)
        if(frDt==""&&toDt==""){
        	return true;
        }
        if (ComGetDaysBetween(frDt, toDt) < 0||isNaN(ComGetDaysBetween(frDt, toDt))){
        	alert('year month illegal');	
            return false;
        }
        return true;
    }
	/**
	 * 
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
//				 {Type : "DelCheck", Hidden : 0, Width : 50, Align : "Center", ColMerge : 0, SaveName : "del_chk" },
				 {Type : "Text",Hidden : 0,Width : 120,Align : "Center",ColMerge : 0,SaveName : "chk",KeyField : 0,Format : "",UpdateEdit : 0,InsertEdit : 1}, 
				 {Type : "Text",Hidden : 0,Width : 100,Align : "Center",ColMerge : 0,SaveName : "jo_crr_cd",KeyField : 1,Format : "",UpdateEdit : 0,InsertEdit : 1}, 
				 {Type : "Combo",Hidden : 0,Width : 200,Align : "Center",ColMerge : 0,SaveName : "rlane_cd",KeyField : 1,Format : "",UpdateEdit : 0,InsertEdit : 1}, 
				 {Type : "Text",Hidden : 0,Width : 100,Align : "Center",ColMerge : 0,SaveName : "vndr_seq",KeyField : 1,Format : "",UpdateEdit : 1,InsertEdit : 1},
				 {Type : "Text",Hidden : 0,Width : 100,Align : "Center",ColMerge : 0,SaveName : "cust_cnt_cd",KeyField : 1,Format : "",UpdateEdit : 1,InsertEdit : 1}, 
				 {Type : "Text",Hidden : 0,Width : 100,Align : "Center",ColMerge : 0,SaveName : "cust_seq",KeyField : 1,Format : "",UpdateEdit : 1,InsertEdit : 1}, 
				 {Type : "Text",Hidden : 0,Width : 120,Align : "Center",ColMerge : 0,SaveName : "trd_cd",KeyField : 0,Format : "",UpdateEdit : 1,InsertEdit : 1},
				 {Type : "Combo",Hidden : 0,Width : 120,Align : "Center",ColMerge : 0,SaveName : "delt_flg",KeyField : 0,Format : "",UpdateEdit : 1,InsertEdit : 1},
				 {Type : "Text",Hidden : 0,Width : 200,Align : "Center",ColMerge : 0,SaveName : "cre_dt",KeyField : 0,Format : "",UpdateEdit : 0,InsertEdit : 0},
				 {Type : "Text",Hidden : 0,Width : 150,Align : "Center",ColMerge : 0,SaveName : "cre_usr_id",KeyField : 0,Format : "",UpdateEdit : 0,InsertEdit : 1},
				 {Type : "Text",Hidden : 0,Width : 200,Align : "Center",ColMerge : 0,SaveName : "upd_dt",KeyField : 0,Format : "",UpdateEdit : 0,InsertEdit : 0},
				 {Type : "Text",Hidden : 0,Width : 100,Align : "Center",ColMerge : 0,SaveName : "upd_usr_id",KeyField : 0,Format : "",UpdateEdit : 0,InsertEdit : 1}];
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
	 * 
	 */
	function loadPage() {
		for (var i = 0; i < sheetObjects.length; i++) {
			ComConfigSheet(sheetObjects[i]);
			initSheet(sheetObjects[i], i + 1)
			ComEndConfigSheet(sheetObjects[i]);
		}	
		/**
		 * SetMultiSelect: This function is used to set whether multiple items will be selected or not.
		 */
		comboObjects[0].SetMultiSelect(1);
		comboObjects[0].SetDropHeight(190);
		comboObjects[0].InsertItem(0, "All", "All");
		comboObjects[0].SetSelectText("All", true);
		var carr = carriers.split("|");
		for (var i = 1; i < carr.length; i++) {
			comboObjects[0].InsertItem(i, carr[i], carr[i]);
		}
//		initPeriod()
		doActionIBSheet(sheetObjects[0],document.form,IBSEARCH)
		
	}
	function initPeriod(){
		var formObj = document.form;
		/**
		 * ComGetNowInfo: get current date
		 */
		var tmpToYm = ComGetNowInfo("ymd","-");
		/**
		 * GetDateFormat: format date
		 */
		formObj.to_ym.value= tmpToYm;
		/**
		 * increase/decrease month
		 */
		var tmpFrYm = ComGetDateAdd(formObj.to_ym.value+"-01","D",-1);
		formObj.fr_ym.value= tmpFrYm;
	}
	
	function setSheetObject(sheet_obj) {
		sheetObjects[sheetCnt++] = sheet_obj;
	}
	/**
	 * 
	 */
	function setComboObject(combo_obj) {
		comboObjects[comboCnt++] = combo_obj;
	}
	/**
	 * 
	 */	
	function resizeSheet() {
		for (var i = 0; i < sheetObjects.length; i++) {
		ComResizeSheet(sheetObjects[i]);
		}
	}
	
    /**
	 * @extends
	 * @class PRACTICE003 : PRACTICE003 생성을 위한 화면에서 사용하는 업무 스크립트를 정의한다.
	 */
    function PRACTICE003() {
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