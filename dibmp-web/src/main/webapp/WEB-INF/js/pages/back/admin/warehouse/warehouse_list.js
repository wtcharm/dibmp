wid = 0 ;

$(function(){
	
	
	
	$("#did").on("change",function(){
		did = $("#did").val() ;	// 取得指定组件的value
		tid = $("#tid").val() ;
		console.log("部门编号：" + did) ;
		loadData() ;
		
	}) ;
	
	$("span[id^=mid-]").each(function(){
		$(this).on("click",function(){
			mid = this.id.split("-")[1] ;
			console.log("仓库管理员编号：" + mid) ;
			$("#memberInfo").modal("toggle") ;
		}) ;
	}) ;
	$("button[id^=editadmin-]").each(function(){
		$(this).on("click",function(){
			wid = this.id.split("-")[1] ;
			console.log("修改仓库管理员，仓库编号：" + wid) ;
			loadData() ; // 异步数据加载与分页控制
			$("#memberDeptInfo").modal("toggle") ;
		}) ;
	}) ;
	
	
	$("#3").on("click",function(){
		val = this.val() ;
		
		console.log(val) ;
	}) ;
	
//	$("button[id^=addadmin-]").each(function(){
//		$(this).on("click",function(){
//			mid = this.id.split("-")[1] ;
//			console.log("新的仓库管理员编号：" + mid) ;
//			name = $("#memberName").text() ; 
//			ele = $("<span id='mid-admin' style='cursor:pointer;'>"+name+"</span>") ;
//			ele.on("click",function(){
//				console.log("仓库管理员ID：" + mid) ;
//				$("#memberInfo").modal("toggle") ;
//			}) ;
//			$("#admin-" + wid).html(ele) ;
//			$("#memberDeptInfo").modal("toggle") ;
//			operateAlert(true,"仓库管理员修改成功！","仓库管理员修改失败！") ;
//		}) ;
//	})
}) ;
function loadData() {	// 该函数名称一定要固定，不许修改
	// 如果要想进行分页的处理列表前首先查询出部门编号
	did = $("#did").val() ;	// 取得指定组件的value
	tid = $("#tid").val() ;
	console.log("部门编号：" + did) ;
	$("#memberBasicInfo tr:gt(0)").remove() ; // 加载之前要进行原有数据删除
	$.post("pages/back/admin/warehouse/listByDid.action",
			{"did":did,"cp":jsCommonCp,"ls":jsCommonLs}, function(data){
				members = data.allMembers ;
				console.log(members.length) ;
				for(y = 0 ; y <members.length ;y++){
					 $("#memberBasicInfo tbody").append("<tr id='travel-"+members[y].mid+"'>" + 
								"	<td class='text-center'>" +
								"		<img src='upload/emp/"+"nophoto.png"+"' style='width:20px;'/> " +
								"	</td>" +
								"	<td class='text-center' id='memberName-"+members[y].name+" ' nameVal='"+members[y].name+"' >"+members[y].name+"</td>" +
								"	<td class='text-center'>"+members[y].lid+"</td>" +
								"	<td class='text-center'>"+members[y].phone+"</td>" +
								"	<td class='text-center'>"+
								"   	<button class='btn btn-danger btn-xs' id='addadmin_" + members[y].name + "'> "+
								"    		<span class='glyphicon glyphicon-plus-sign'></span>&nbsp;设置为库管" +
								"  		</button> "  +
								"	</td>" +
								"</tr> ") ;
					// console.log($("#memberName-"+members[y].mid).text()) ;
				}
				createSplitBar(data.allCounts) ;   // 创建分页控制项
				$("button[id^=addadmin_]").each(function(){
					//console.log($("#memberName-mldn26").text()) ;
					$(this).on("click",function(){
						//console.log(this.id) ;
						mid = this.id.split("_")[1] ;
						
						console.log("新的仓库管理员编号：" + mid) ;
						//console.log("仓库编号：" + wid) ;
						nameId = "memberName-"+mid ;
						//name = $("#"+nameId).attr("nameval") ;
						name = $("#"+nameId).html() ;
						console.log("姓名Id：" + name) ;
						flag = "false" ;
						$.post("pages/back/admin/warehouse/addAdmin.action",{"wid":wid,"admin":mid},function(data){
//							flag = data ;
//							console.log("flag======：" + flag) ;
							
						},"text") ;
						
						
						//console.log("flag：" + flag) ;
						console.log("姓名：" + name) ;
						ele = $("<span id='mid-"+wid+"' style='cursor:pointer;'>"+mid+"</span>") ;
						ele.on("click",function(){
							console.log("仓库管理员ID：" + mid) ;
							$("#memberInfo").modal("toggle") ;
						}) ;
						$("#admin-" + wid).html(ele) ;
						$("#memberDeptInfo").modal("toggle") ;
						operateAlert(true,"仓库管理员修改成功！","仓库管理员修改失败！") ;
					}) ;
				})
		},"json") ;
	
}