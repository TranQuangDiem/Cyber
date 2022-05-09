/*=========================================================
*Copyright(c) 2022 CyberLogitec
*@FileName : PRACTICE003.js
*@FileTitle : Practice 3
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
 * eclare list comboObject
 */
var comboObjects = new Array();
var comboCnt = 0;
/**
 * declare list tabObject
 */
var tabObjects = new Array();
var tabCnt = 0;
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
			var sXml2 =sheetObj.GetSearchData("Practice003GS.do",FormQueryString(formObj));
			/**
			 * LoadSearchData :Get search data (xml or json) as a method parameter and load to IBSheet.
			 * This method can be used to read encrypted search data when security module is used.
			 * Search data are retrieved using GetSearchData method from the server.
			 * The data string fetched is set as a method parameter. The search data are loaded to IBSheet and OnSearchEnd event fires.
			 */
			sheetObj.LoadSearchData(sXml2, {Sync : 1});
			subsum(sheetObj);
			/**
			 * get EtcData
			 */
			var total = ComGetEtcData(sXml2, "toal");
			totalSum(sheetObj,total);
			break;
		case 'IBSEARCH01':
			formObj.f_cmd.value = SEARCH01;
			/**
			 * GetSearchData: Call search page, complete search and return search result data in string.
			 * Unlike DoSearch, this method returns search result data itself without processing search result.
			 * Search result data returned by this method can be loaded to IBSheet if you use them as LoadSearchData parameter.
			 */
			var sXml2 =sheetObj.GetSearchData("Practice003GS.do",FormQueryString(formObj));
			/**
			 * LoadSearchData :Get search data (xml or json) as a method parameter and load to IBSheet.
			 * This method can be used to read encrypted search data when security module is used.
			 * Search data are retrieved using GetSearchData method from the server.
			 * The data string fetched is set as a method parameter. The search data are loaded to IBSheet and OnSearchEnd event fires.
			 */
			sheetObj.LoadSearchData(sXml2, {Sync : 1});
			subsum(sheetObj);
			totalSum(sheetObj);
			break;
		}

	}
	
	function subsum(sheetObj) {
		var info;
		if(sheetObj.id==='sheet1'){
			/**
			 * StdCol: Reference column index or SaveName
			 * SumCols:  String of column indexes where sub sum should be calculated,adjoined by“|”.
			 * Sort:  Whether the reference column is sorted. To display sub sum,values in reference column must have been sorted.Default=1
			 * ShowCumulate: Whether to display aggregate total of sub sums Default=0
			 * CaptionCol: Column to set sub sum caption text as “Sub sum : “ + reference value Default=The first unhidden column
			 * CaptionText: Set sub sum caption text format Default=sub sum (aggregate total): + reference value
			 */
			info = [{StdCol:3, SumCols:"7|8",ShowCumulate: 0,CaptionCol:3,CaptionText:' ', Sort:1}];
		
		}else {
			info = [{StdCol:3, SumCols:"9|10", ShowCumulate:0,CaptionCol:3,CaptionText:' ', Sort:1}];
		}
		/**
		 * ShowSubSum: Calculate sub sums and total sum for a column.This method should be called before data search method is called.
		 *  When configuration is changed, you need to rerun a data search to apply it.
		 */
		sheetObj.ShowSubSum(info);
		sheetObj.GetSumFontBold();
		if(sheetObj.id==='sheet1'){
			for (var i = 1; i <= sheetObj.LastRow(); i++) {
				if(sheetObj.GetCellValue(i, "jo_crr_cd")==''){
					/**
 					 * SetCellValue :Set value in row ,column, new value
 					 * GetCellvalue : get value in row,column
 					 * 
 					 */
					sheetObj.SetCellValue(i, 6,sheetObj.GetCellValue(i-1, "locl_curr_cd"));
				}
				
			}
		}else{
			for (var i = 1; i <= sheetObj.LastRow(); i++) {
				if(sheetObj.GetCellValue(i, "jo_crr_cd")==''){
					sheetObj.SetCellValue(i, 8,sheetObj.GetCellValue(i-1, "locl_curr_cd"));
				}
				
			}
		}
	}
	/**
	 * 
	 */
	function totalSum(sheetObj,total){
		if(sheetObjects[ibsheet].RowCount() >= 1){
			var tong = total.substring(0,total.length-1).split("|");
			for(var i =0 ; i<tong.length ; i++){
				var result = tong[i].split(",");
				/**
				 * DataInsert: Create a new data row, and return the row index of the new row
				 * @Syntax: ObjId.DataInsert([Row])
				 * @Param : Row < 0 :		 		Create as the last row
				 * 			Row >= All rows :		Create as the last row
				 * 			Row >= First data row : Create as the first row
				 *			Default :				Create below the selected row
				 */
				sheetObj.DataInsert(-1);
				if(sheetObj.id==='sheet1'){
					/**
 					 * SetCellValue :Set value in row ,column, new value
 					 * GetCellvalue : get value in row,column
 					 * 
 					 */
					sheetObj.SetCellValue(sheetObj.LastRow(), 6,result[0]);
					sheetObj.SetCellValue(sheetObj.LastRow(), 7,result[1]);
					sheetObj.SetCellValue(sheetObj.LastRow(), 8,result[2]);
					sheetObj.SetRowBackColor(sheetObj.LastRow(),"#fdbb8f");
				}else{
					sheetObj.SetCellValue(sheetObj.LastRow(), 8,result[0]);
					sheetObj.SetCellValue(sheetObj.LastRow(), 9,result[1]);
					sheetObj.SetCellValue(sheetObj.LastRow(), 10,result[2]);
					sheetObj.SetRowBackColor(sheetObj.LastRow(),"#fdbb8f");
				}
			}	
		}
	}
//	function totalSum(sheetObj,total){
//		if(sheetObjects[ibsheet].RowCount() >= 1){
//		var total = new Array();
//		var curr;
//		var revenue;
//		var expense;
//		var totalrevenue=0;
//		var totalexpense=0;
//		var totalrevenueUSD=0;
//		var totalexpenseUSD=0;
//		for( var i = sheetObj.LastRow(); i >= sheetObj.HeaderRows(); i-- ) {
//			curr=sheetObj.GetCellValue(i, "locl_curr_cd");
//			revenue=Number(sheetObj.GetCellValue(i, "inv_rev_act_amt"));
//			expense=Number(sheetObj.GetCellValue(i, "inv_exp_act_amt"));
//			if(curr==='VND'){
//				totalrevenue+=revenue;
//				totalexpense+=expense;
//			}else if(curr==='USD'){
//				totalrevenueUSD+=revenue;
//				totalexpenseUSD+=expense
//			}
//			
//		}
//			sheetObj.DataInsert(-1);
//			sheetObj.SetCellValue(sheetObj.LastRow(), 6,"VND");
//			sheetObj.SetCellValue(sheetObj.LastRow(), 7,totalrevenue);
//			sheetObj.SetCellValue(sheetObj.LastRow(), 8,totalexpense);
//			sheetObj.SetRowBackColor(sheetObj.LastRow(),"#fdbb8f");
//			sheetObj.DataInsert(-1);
//			sheetObj.SetCellValue(sheetObj.LastRow(), 6,"USD");
//			sheetObj.SetCellValue(sheetObj.LastRow(), 7,totalrevenueUSD);
//			sheetObj.SetCellValue(sheetObj.LastRow(), 8,totalexpenseUSD);
//			sheetObj.SetRowBackColor(sheetObj.LastRow(),"#fdbb8f");
//			
//		}
//	}
	/**
	 * 
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
				if(getCheckConditionPeriod()){
					if(getCheckYearMonth()){
						doActionIBSheet(sheetObjects[0], formObj, IBSEARCH);
						doActionIBSheet(sheetObjects[1], formObj, IBSEARCH);
					}
				}
				break;
			case "btn_from_back":
				if(getCheckYearMonth()){	
						addMonth(formObj.fr_yrmon, -1);
					}
				break;
			case "btn_from_next":
				if(getCheckConditionPeriod()){
					addMonth(formObj.fr_yrmon, 1)
				}
				break;
			case "btn_to_back":
				if(getCheckConditionPeriod()){
					addMonth(formObj.to_yrmon, -1);
				}
				break;
			case "btn_to_next":
//				var tmpToYm = ComGetNowInfo("ymd","-");
//				var day= GetDateFormat(tmpToYm,"ym").replaceStr("-","")+"01";
//				console.log(ComGetDaysBetween(day, formObj.fr_yrmon));
//				if(ComGetDaysBetween(day, formObj.fr_yrmon)<-31){
//					alert('year month illegal');	
//				}else if(getCheckYearMonth()){
				if(getCheckConditionPeriod()&&getCheckYearMonth()){
					addMonth(formObj.to_yrmon, 1);
				}
					
//				}
				break;
			case "btn_DownExcel":
				if (sheetObjects[ibsheet].RowCount() < 1) {// no data
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
					sheetObjects[ibsheet].Down2Excel({DownCols: makeHiddenSkipCol(sheetObjects[0]), SheetDesign:1, Merge:1});
				}
				break;
			case "btn_New":
				initPeriod()
				for(var i =0 ; i < comboObjects.length; i++){
				comboObjects[i].SetSelectCode( -1, true);
				}
				doActionIBSheet(sheetObjects[0], formObj, IBSEARCH);
				doActionIBSheet(sheetObjects[1], formObj, IBSEARCH);
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
	 * 
	 */
	function initSheet(sheetObj, sheetNo) {
		switch (sheetNo) {
		case 1:
			with (sheetObj) {
				var HeadTitle1 = "STS|Partner|Lane|Invoice No|Slip No|Approved|Curr|Revenue|Expense|Customer/S.Provider|Customer/S.Provider";
				var HeadTitle = "STS|Partner|Lane|Invoice No|Slip No|Approved|Curr|Revenue|Expense|Code|Name";
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
				var headers = [ {Text : HeadTitle1,Align : "Center"},
				                {Text : HeadTitle,Align : "Center"}];
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
				 {Type : "Text",Hidden : 0,Width : 120,Align : "Center",ColMerge : 0,SaveName : "jo_crr_cd",KeyField : 0,Format : "",UpdateEdit : 0,InsertEdit : 0}, 
				 {Type : "Text",Hidden : 0,Width : 100,Align : "Center",ColMerge : 0,SaveName : "rlane_cd",KeyField : 0,Format : "",UpdateEdit : 0,InsertEdit : 0}, 
				 {Type : "Text",Hidden : 0,Width : 200,Align : "Center",ColMerge : 0,SaveName : "inv_no",KeyField : 0,Format : "",UpdateEdit : 0,InsertEdit : 0}, 
				 {Type : "Text",Hidden : 0,Width : 300,Align : "Center",ColMerge : 0,SaveName : "csr_no",KeyField : 0,Format : "",UpdateEdit : 0,InsertEdit : 0},
				 {Type : "Text",Hidden : 0,Width : 100,Align : "Center",ColMerge : 0,SaveName : "apro_flg",KeyField : 0,Format : "",UpdateEdit : 0,InsertEdit : 0}, 
				 {Type : "Text",Hidden : 0,Width : 120,Align : "Center",ColMerge : 0,SaveName : "locl_curr_cd",KeyField : 0,Format : "",UpdateEdit : 0,InsertEdit : 0},
				 {Type : "Text",Hidden : 0,Width : 120,Align : "Center",ColMerge : 0,SaveName : "inv_rev_act_amt",KeyField : 0,Format : "",UpdateEdit : 0,InsertEdit : 0},
				 {Type : "Text",Hidden : 0,Width : 100,Align : "Center",ColMerge : 0,SaveName : "inv_exp_act_amt",KeyField : 0,Format : "",UpdateEdit : 0,InsertEdit : 0},
				 {Type : "Text",Hidden : 0,Width : 100,Align : "Center",ColMerge : 0,SaveName : "prnr_ref_no",KeyField : 0,Format : "",UpdateEdit : 0,InsertEdit : 0},
				 {Type : "Text",Hidden : 0,Width : 100,Align : "Center",ColMerge : 0,SaveName : "cust_vndr_eng_nm",KeyField : 0,Format : "",UpdateEdit : 0,InsertEdit : 0}];
				/**
				 * Check or configure overall editability.If overall editing is not allowed, all cells become uneditable regardless of other settings.
				 */
				SetEditable(1);
				/**
				 * Configure data type, format and functionality of each column.
				 */
				InitColumns(cols);
				resizeSheet();
			}
			break;
		case 2:
			with (sheetObj) {
				var HeadTitle1 = "STS|Partner|Lane|Invoice No|Slip No|Approved|Rev/Exp|Item|Curr|Revenue|Expense|Customer/S.Provider|Customer/S.Provider";
				var HeadTitle = "STS|Partner|Lane|Invoice No|Slip No|Approved|Rev/Exp|Item|Curr|Revenue|Expense|Code|Name";
				var headCount = ComCountHeadTitle(HeadTitle);
				SetConfig({SearchMode : 2,MergeSheet : 5,Page : 40,DataRowMerge : 1});
				var info = {Sort : 1,ColMove : 1,HeaderCheck : 0,ColResize : 1};
				var headers = [ {Text : HeadTitle1,Align : "Center"},
				                {Text : HeadTitle,Align : "Center"}];
				InitHeaders(headers, info);
				var cols = [ {Type : "Status",Hidden : 1,Width : 50,Align : "Center",ColMerge : 0,SaveName : "ibflag"},
	//			 {Type : "DelCheck", Hidden : 0, Width : 50, Align : "Center", ColMerge : 0, SaveName : "del_chk" },
				 {Type : "Text",Hidden : 0,Width : 120,Align : "Center",ColMerge : 0,SaveName : "jo_crr_cd",KeyField : 0,Format : "",UpdateEdit : 0,InsertEdit : 0}, 
				 {Type : "Text",Hidden : 0,Width : 100,Align : "Center",ColMerge : 0,SaveName : "rlane_cd",KeyField : 0,Format : "",UpdateEdit : 0,InsertEdit : 0}, 
				 {Type : "Text",Hidden : 0,Width : 200,Align : "Center",ColMerge : 0,SaveName : "inv_no",KeyField : 0,Format : "",UpdateEdit : 0,InsertEdit : 0}, 
				 {Type : "Text",Hidden : 0,Width : 300,Align : "Center",ColMerge : 0,SaveName : "csr_no",KeyField : 0,Format : "",UpdateEdit : 0,InsertEdit : 0},
				 {Type : "Text",Hidden : 0,Width : 100,Align : "Center",ColMerge : 0,SaveName : "apro_flg",KeyField : 0,Format : "",UpdateEdit : 0,InsertEdit : 0}, 
				 {Type : "Text",Hidden : 0,Width : 100,Align : "Center",ColMerge : 0,SaveName : "rev_exp",KeyField : 0,Format : "",UpdateEdit : 0,InsertEdit : 0}, 
				 {Type : "Text",Hidden : 0,Width : 100,Align : "Center",ColMerge : 0,SaveName : "jo_stl_itm_cd",KeyField : 0,Format : "",UpdateEdit : 0,InsertEdit : 0},
				 {Type : "Text",Hidden : 0,Width : 120,Align : "Center",ColMerge : 0,SaveName : "locl_curr_cd",KeyField : 0,Format : "",UpdateEdit : 0,InsertEdit : 0},
				 {Type : "Text",Hidden : 0,Width : 120,Align : "Center",ColMerge : 0,SaveName : "inv_rev_act_amt",KeyField : 0,Format : "",UpdateEdit : 0,InsertEdit : 0},
				 {Type : "Text",Hidden : 0,Width : 100,Align : "Center",ColMerge : 0,SaveName : "inv_exp_act_amt",KeyField : 0,Format : "",UpdateEdit : 0,InsertEdit : 0},
				 {Type : "Text",Hidden : 0,Width : 100,Align : "Center",ColMerge : 0,SaveName : "prnr_ref_no",KeyField : 0,Format : "",UpdateEdit : 0,InsertEdit : 0},
				 {Type : "Text",Hidden : 0,Width : 100,Align : "Center",ColMerge : 0,SaveName : "cust_vndr_eng_nm",KeyField : 0,Format : "",UpdateEdit : 0,InsertEdit : 0}];
				SetEditable(1);
				InitColumns(cols);
//				SetSheetHeight(715);
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
		partner.SetMultiSelect(1);
//		partner.SetMultiSeparator(",");
		/**
		 * SetDropHeight : set height
		 */
		partner.SetDropHeight(190);
		var p = partners.substring(1,partners.length-1).split(", ");
		partner.InsertItem(0, "All", "All");
		for(var i = 1 ; i < p.length ; i++){
			/**
			 * InsertItem: Add an item. Set the text value using a “|” separator when using multicolumn.Add to the last row, if the Index parameter is -1.
			 * Syntax Obj.InsertItem(Index, Text, Code);
			 * @param Index: Row index of the item to add
			 * @param Text: Text of the item to add
			 * @param Code: Code of the item to add If not entered, the Index value is converted to a string and used as the code value.
			 */
			partner.InsertItem(i, p[i], p[i]);
		}

//		partner.GetItemXml( "Practice003GS.do", "f_cmd="+SEARCH01);
		lane.SetDropHeight(190);
		
		trade.SetMultiSelect(0);
		trade.SetDropHeight(190);
	
		for(k = 0;k < tabObjects.length; k++){
			initTab(tabObjects[k], k + 1);
			tabObjects[k].SetSelectedIndex(0);
		}
		partner.SetSelectText("All", true);
		initControl();
		doActionIBSheet(sheetObjects[0], document.form, IBSEARCH);
		doActionIBSheet(sheetObjects[1], document.form, IBSEARCH);
		
	}
	/**
	 * 
	 */
	function initControl() {
		var formObject = document.form;
		initPeriod();
	}
	/**
	 * 
	 */
	function initPeriod(){
		var formObj = document.form;
		/**
		 * ComGetNowInfo: get current date
		 */
		var tmpToYm = ComGetNowInfo("ymd","-");
		/**
		 * GetDateFormat: format date
		 */
		formObj.to_yrmon.value= GetDateFormat(tmpToYm,"ym");
		/**
		 * increase/decrease month
		 */
		var tmpFrYm = ComGetDateAdd(formObj.to_yrmon.value+"-01","M",-1);
		formObj.fr_yrmon.value= GetDateFormat(tmpFrYm, "ym")
	}
	/**
	 * 
	 */
	function GetDateFormat(obj, sFormat) {
		var sVal = String(getArgValue(obj));
		sVal = sVal.replace(/\/|\-|\.|\:|\ /g,"");
		
		if(ComIsEmpty(sVal)) return "";
		
		var retValue = "";
		switch(sFormat) {
		case "ym":
			retValue = sVal.substring(0,6);
		}
		retValue = ComGetMaskedValue(retValue,sFormat);
		return retValue;
	}
	/**
	 * 
	 */
	var flag = false;
	function getCheckYearMonth(){
		 var formObj=document.form;
	        var frDt=formObj.fr_yrmon.value.replaceStr("-","")+"01";
	        var toDt=formObj.to_yrmon.value.replaceStr("-","")+"01";
		if (ComGetDaysBetween(formObj.fr_yrmon, formObj.to_yrmon)>=90){
        	if(!flag){
				let choice =confirm("Year Month over 3 months, do you realy want to get data?");
				if(choice){
					flag = true
					return true
				}else{
					 return false;
				}
        	}
        }
		return true;
	}
	function getCheckConditionPeriod(){
//		var regex = new RegExp("^[0-9]{4}-[0-1][0-9]");
//		if(!regex.test(formObj.fr_yrmon.value)||!regex.test(formObj.to_yrmon.value)){
//			alert('year month illegal');	
//            return false;
//		}
        var formObj=document.form;
        var frDt=formObj.fr_yrmon.value.replaceStr("-","")+"01";
        var toDt=formObj.to_yrmon.value.replaceStr("-","")+"01";
        if (ComGetDaysBetween(frDt, toDt) <= 0){
        	alert('year month illegal');	
            return false;
        }
        return true;
    }
	/**
	 * 
	 */
    function addMonth(obj, month) {
    	if(obj.value != "") {
    		obj.value=ComGetDateAdd(obj.value+"-01", "M", month).substring(0,7);
    	}
		
	}
    /**
	 * 
	 */
    function geneDataCombo(comboObj, dataStr){
    	if(typeof dataStr !== 'undefined'){
    		if(dataStr.indexOf("|") != -1){
    			var data = dataStr.split("|");
    			for(var j=0; j<data.length; j++){
    				comboObj.InsertItem(-1, data[j], data[j]);
    			}
    		}
    		if(dataStr.length > 0 && dataStr.indexOf("|") == -1){
    			comboObj.InsertItem(-1, dataStr, dataStr);
    		}
    	}
    }
    /**
	 * 
	 */
	var partnerValue = '';
	function partner_OnChange(OldIndex, OldText, OldCode, NewIndex, NewText,NewCode) {
		partnerValue = NewCode;
		var xml = sheetObjects[0].GetSearchData( "Practice003GS.do", "f_cmd="+SEARCH02+"&jo_crr_cd="+NewCode );
		laneCb = ComGetEtcData(xml, "laneCb");
		comboObjects[1].RemoveAll();
		comboObjects[2].RemoveAll();
		geneDataCombo(comboObjects[1], laneCb);
	}
	/**
	 * 
	 */
	function lane_OnChange(OldIndex, OldText, OldCode, NewIndex, NewText,NewCode) {
		var xml = sheetObjects[0].GetSearchData( "Practice003GS.do", "f_cmd="+SEARCH03+"&jo_crr_cd="+partnerValue+"&rlane_cd="+NewCode);
		tradeCb = ComGetEtcData(xml, "tradeCb");
		comboObjects[2].RemoveAll();
		geneDataCombo(comboObjects[2], tradeCb);
	}
	/**
	 * 
	 */
	function initTab(tabObj , tabNo) {
		switch(tabNo) {
		case 1:
			with (tabObj) {
				var cnt=0 ;
					InsertItem( "Summary" , "");
					InsertItem( "Detail" , "");
			}
			break;
		}
	}
	/**
	 * 
	 */
	function tab1_OnChange(tabObj, nItem){
		ibsheet = nItem;
		var beforetab =1;
		var objs=document.all.item("tabLayer");
		objs[nItem].style.display="Inline";		
		//--------------- this is important! --------------------------//
		for(var i = 0; i<objs.length; i++){
			  if(i != nItem){
			   objs[i].style.display="none";
			   objs[beforetab].style.zIndex=objs[nItem].style.zIndex = 1 ;
			  }
			}
		//------------------------------------------------------//
		beforetab=nItem;
	    resizeSheet();
//	    var formObj = document.form;
//		 formObj.partner.value  = '';
//		 formObj.lane.value = '';
//	    doActionIBSheet(sheetObjects[0], formObj, IBSEARCH);
//		doActionIBSheet(sheetObjects[1], formObj, IBSEARCH);
	} 
	/**
	 * 
	 */
	function sheet1_OnDblClick(sheetObj, Row, Col) {
		 if(getCheckConditionPeriod()){
			if(getCheckYearMonth()){
//				var objs=document.all.item("tabLayer");
//				 objs[0].style.display="none";
//				 objs[1].style.display="Inline";
//				 objs[0].style.zIndex=objs[1].style.zIndex = 1 ;
				 ibsheet = 1;
//		 document.getElementById("tabTabDIV_tab1_1").setAttribute("class", "tabActive");
//		 document.getElementById("tabTabDIV_tab1_0").setAttribute("class", "tabInactive");
		 
//				 var formObj = document.form;
//				 formObj.partner.value  = sheetObjects[0].GetCellValue(Row, "jo_crr_cd");
//				 formObj.lane.value = sheetObjects[0].GetCellValue(Row, "rlane_cd");
//				 doActionIBSheet(sheetObjects[1], formObj, IBSEARCH);
//				 selectRowToOtherSheet(sheetObj,sheetObjects[1],Row,2);
				 sheetObjects[1].SetSelectRow(Row);
				 tab1.SetSelectedIndex(1);
				 
			}
		 }
		
	}
	/**
	 * 
	 */
	function setTabObject(tab_obj){
		tabObjects[tabCnt++]=tab_obj;
	}
	/**
	 * 
	 */
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
	 * 
	 */
	function setTabObject(tab_obj) {
		tabObjects[tabCnt++] = tab_obj;
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