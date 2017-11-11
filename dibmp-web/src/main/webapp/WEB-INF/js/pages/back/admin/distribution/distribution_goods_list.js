$(function() {
	$("#allPrice").text(calSum()) ;
	$("#selectAll").on("click",function(){
		checkboxSelectAll('gid',this.checked) ;
	}) ;
	// 设置修改单独数量的操作
	$("button[id*=updateBtn-]").each(function(){
		var gid = this.id.split("-")[1] ;
		var price = $("span[id^=price-"+gid+"]").text();
		var name = $("span[id^=name-"+gid+"]").text();
		var stornum = $("span[id^=stornum-"+gid+"]").text();
		$(this).on("click",function(){
			var num = parseInt($("#amount-" + gid).val()) ;	// 直接取得value属性
			if (num == 0) {
				$("#goods-" + gid).remove() ;
			}
			$.post("pages/back/admin/goods/updateUnStorageGoods.action",
					{"gid":gid,"price":price,"name":name,"stornum":stornum,"num":num},function(data){
						if(data == "true"){
							operateAlert(true,"商品数量修改成功！","商品数量修改失败！") ;
						}else{
							operateAlert(false,"商品数量修改成功！","商品数量修改失败！") ;
						}
			},"json")
		}) ;
	}) ;  
	
	$(editBtn).on("click",function(){
		var delGid = new Array() ;
		var foot = 0 ;
		var data = "" ; 
		$("[id*=amount-]").each(function(){
			var gid = this.id.split("-")[1] ;
			var amount = this.value ;
			if (amount != "0") {
				data += gid + ":" + amount + "|" ;
			} else {
				delGid[foot ++] = gid ;
			}
		}) ;
		// 进行ajax异步数据处理操作
		//operateAlert(true,"商品数量修改成功！","商品数量修改失败！") ;
	}) ;
	$("#rmBtn").on("click",function(){	// 绑定用户锁定操作
		var data = "" ;
		$(":checked").each(function() {
			if(this.id == "gid") {	// 要删除的内容
				data += this.value + "," ;
			}
		}) ;
		$.post("pages/back/admin/goods/deleteGoods",{"ids":data},function(data){
			if(data == "true"){
				if (data != "") {
					$(":checked").each(function() {
						$("#goods-" + this.value).remove() ;
					});
				}
				operateAlert(true,"商品信息移除成功！","商品信息移除失败！") ;
			}else{
				operateAlert(false,"商品信息移除成功！","商品信息移除失败！") ;
			}
		},"json")
	}) ;
	$("button[id*=add-]").each(function(){
		var gid = this.id.split("-")[1] ; // 取得商品ID数据
		$(this).on("click",function(){
			var amount = parseInt($("#amount-" + gid).val()) ;	// 直接取得value属性
			$("#amount-" + gid).val(amount+1) ;
			$("#allPrice").text(calSum()) ;
		})
	}) ;
	$("button[id*=sub-]").each(function(){
		var gid = this.id.split("-")[1] ; // 取得商品ID数据
		$(this).on("click",function(){
			var amount = parseInt($("#amount-" + gid).val()) ;	// 直接取得value属性
			if (amount > 0) {
				$("#amount-" + gid).val(amount - 1) ;
				$("#allPrice").text(calSum()) ;
			}
		})
	}) ; 
	
	$(createBtn).on("click",function(){
		var ids = "" ;
		var sumAmount = 0 ;
		var sumPrice = 0.0;
		$(":checked").each(function() {
			if(this.id == "gid") {
				ids += this.value + "," ;
			}
		}) ;
		if(ids !=""){
			$("[id*=amount-]").each(function(){
				var gid = this.id.split("-")[1] ;
				var  amount = parseInt(this.value) ;
				if(amount != 0){
					sumAmount += amount;
				}
			})
			$("span[id*=price-]").each(function(){
				var gid = this.id.split("-")[1];
				var price = $(this).text();
				var amount = $("#amount-" + gid).val() ;
				sumPrice += parseFloat(price) * parseInt(amount) ; 
			});
		}
		$.post("pages/back/admin/goods/calculateAmountAndSumPrice",{"amount":sumAmount,"sumPrice":sumPrice,"ids":ids},function(data){
			if(data == "true"){
				window.location = "http://localhost:80/dibmp/pages/back/admin/goods/add_pre";
			}else{
				window.location = "http://localhost:80/dibmp/pages/back/admin/goods/unStorageGoods";
			}
		},"json");
	});
})





function calSum() {
	var sumPrice = 0.0 ;	// 保存总价
	// 计算购买的商品的总价数据
	$("span[id*=price-]").each(function(){
		var gid = this.id.split("-")[1] ; // 取得商品ID数据
		var price = $(this).text() ;	// 取得文本内容
		var amount = $("#amount-" + gid).val() ;	// 直接取得value属性
		sumPrice += parseFloat(price) * parseInt(amount) ;
	}) ;
	return round(sumPrice,2) ;
}