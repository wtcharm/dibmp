cid = 0 ;
$(function(){
	$("span[id^=mid-]").each(function(){
		$(this).on("click",function(){
			mid = this.id.split("-")[1] ;
			$("#memberInfo").modal("toggle") ;
		}) ;
	}) ;
	/*$("span[id^=cid-]").each(function(){
		$(this).on("click",function(){
			cuid = this.id.split("-")[1] ;
			console.log(cuid);
			alert(loadData(cuid));
			loadData(cuid) ;
			$("#customerRecordInfo").modal("toggle") ;
			
		}) ;
	}) ;*/
	$("span[id^=cid-]").each(function(){
		$(this).on("click",function(){
			var  date= new Date();
			var data ;
			cuid = this.id.split("-")[1] ;
			$.post("pages/back/admin/customer/ListCustomerRecord.action",{"cuid":cuid,"cuid":cuid},function(data){
				$("#mycuid").empty() ;
				for(var i = 0 ; i < data.allCr.length ; i ++){
					cr = data.allCr[i] ;
					trInfo = 	$("<tr id='record-1'>"+
								"<td class='text-center'>"+datetimeFormat_1(cr.cdate)+"</td>"+
								"<td class='text-left'>"+cr.cmid+"</td>"+
								"<td class='text-left'>01051283346</td>"+
								"<td class='text-left'>"+
									"<pre class='pre-scrollable' style='width:700px;height:60px;'>"+cr.note+"</pre>"+
								"</td>"+
							"</tr> ") ;
					$("#mycuid").append(trInfo) ;
				}
				//loadData() ;
				$("#customerRecordInfo").modal("toggle") ;
			},"json") ;
		}) ;
	}) ;
	$("button[id^=out-]").each(function(){
		$(this).on("click",function(){
			cid = this.id.split("-")[1] ;
			operateAlert(true,"出库客户追加成功！","出库客户追加失败！") ;
		}) ;
	}) ;
	$("button[id^=input-]").each(function(){
		$(this).on("click",function(){
			cid = this.id.split("-")[1] ;
			$("#customerRecordInputInfo").modal("toggle") ;
		}) ;
	}) ;
	$("#myform").validate({
		debug : true, // 取消表单的提交操作
		submitHandler : function(form) {
			// 发送ajax请求进行异步数据处理操作
			$("#customerRecordInputInfo tr:gt(0)").remove()
			$("#customerRecordInputInfo").modal("toggle") ;
			title = $(title).val() ;
			criid = $(criid).val() ;
			note = $(note).val() ;
			$.post("pages/back/admin/customer/add_customerrecord.action",{"title":title,"criid":criid,"note":note,"cuid":cid},
					function(data){
				    console.log(data);
					if(data.trim()=="true"){
						operateAlert(true,"客户联系记录追加成功！","客户联系记录追加失败！") ;
					}else{
						operateAlert(false,"客户联系记录追加成功！","客户联系记录追加失败！") ;
					}
			},"json") ;
			operateAlert(true,"客户联系记录追加成功！","客户联系记录追加失败！") ;
		},
		errorPlacement : function(error, element) {
			$("#" + $(element).attr("id").replace(".", "\\.") + "Msg").append(error);
		},
		highlight : function(element, errorClass) {
			$(element).fadeOut(1,function() {
				$(element).fadeIn(1, function() {
					$("#" + $(element).attr("id").replace(".","\\.") + "Div").attr("class","form-group has-error");
				});
                
			})
		},
		unhighlight : function(element, errorClass) {
			$(element).fadeOut(1,function() {
				$(element).fadeIn(1,function() {
						$("#" + $(element).attr("id").replace(".","\\.") + "Div").attr("class","form-group has-success");
				});
			})
		},
		errorClass : "text-danger",
		rules : {
			"title" : {
				required : true
			} ,
			"criid" : {
				required : true
			} ,
			"note" : { 
				required : true 
			}
		}
	});
}) ;
function loadData(cid) {	// 该函数名称一定要固定，不许修改
	// 如果要想进行分页的处理列表前首先查询出部门编号
	console.log("客户编号：" + cid) ;
    $.post("pages/back/admin/customer/ListCustomerRecord.action",{"cuid":cid},function(data){
    	console.log(data);
    	$("#mycuid").remove();
    	console.log(data.allRecorders)
    	for (x = 0 ; x < data.allRecorders; x ++) {
    		var row = "<tr id='record-'"+data.allCustomerRecords[x].crid+"'>" +
    				"<td class='text-center'>"+data.allCustomerRecords[x].cdate+"</td>" +
    				//"<td class='text-left'>"+data.member.name+"</td><td class='text-left'>"+data.member.phone+"</td>" +
    				"<td class='text-left'><pre class='pre-scrollable' style='width:700px;height:60px;'>"+data.allCustomerRecords[x].note+"</pre></td>" +
    				"</tr>";
    				
    		$("#mycuid").append(row);
    	}
	},"json") ;
	// $("#memberBasicInfo tr:gt(0)").remove() ; // 加载之前要进行原有数据删除
	createSplitBar(10) ;	// 创建分页控制项
}
function datetimeFormat_1(longTypeDate){ 
	  var datetimeType = ""; 
	  var date = new Date(); 
	  date.setTime(longTypeDate); 
	  datetimeType+= date.getFullYear();  //年 
	  datetimeType+= "-" + getMonth(date); //月  
	  datetimeType += "-" + getDay(date);  //日 
//	  datetimeType+= "  " + getHours(date);  //时 
//	  datetimeType+= ":" + getMinutes(date);   //分
//	  datetimeType+= ":" + getSeconds(date);   //分
	  return datetimeType;
	} 
	//返回 01-12 的月份值  
	function getMonth(date){ 
	  var month = ""; 
	  month = date.getMonth() + 1; //getMonth()得到的月份是0-11 
	  if(month<10){ 
	    month = "0" + month; 
	  } 
	  return month; 
	} 
	//返回01-30的日期 
	function getDay(date){ 
	  var day = ""; 
	  day = date.getDate(); 
	  if(day<10){ 
	    day = "0" + day; 
	  } 
	  return day; 
	}
	//返回小时
	function getHours(date){
	  var hours = "";
	  hours = date.getHours();
	  if(hours<10){ 
	    hours = "0" + hours; 
	  } 
	  return hours; 
	}
	//返回分
	function getMinutes(date){
	  var minute = "";
	  minute = date.getMinutes();
	  if(minute<10){ 
	    minute = "0" + minute; 
	  } 
	  return minute; 
	}
	//返回秒
	function getSeconds(date){
	  var second = "";
	  second = date.getSeconds();
	  if(second<10){ 
	    second = "0" + second; 
	  } 
	  return second; 
	}