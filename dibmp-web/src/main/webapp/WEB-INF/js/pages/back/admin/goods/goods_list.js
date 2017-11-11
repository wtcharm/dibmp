$(function(){
	$("button[id^=out-]").each(function(){
		$(this).on("click",function(){
			gid = this.id.split("-")[1] ;
			var price = $("span[id^=price-"+gid+"]").text();
			var name = $("span[id^=name-"+gid+"]").text();
			var stornum = $("span[id^=stornum-"+gid+"]").text();
			var weight = $("span[id^=weight-"+gid+"]").text();
			var num = 1;
			$.post("pages/back/admin/goods/unStorage.action",
					{"gid":gid,"price":price,"stornum":stornum,"num":num,"name":name,"weight":weight},function(data){
						if(data == "true"){
							operateAlert(true,"待出库商品添加成功！","待出库商品添加失败！") ;
						}else{
							operateAlert(false,"待出库商品添加成功！","待出库商品添加失败！") ;
						}
		},"json")
		}) ;
	}) ;
	$("span[id^=storage-]").each(function(){
		$(this).on("click",function(){
			mid = this.id.split("-")[1] ;
			$("#goodsRecordInfo").modal("toggle") ; 
		}) ;
	}) ;
	$("span[id^=mid-]").each(function(){
		$(this).on("click",function(){
			mid = this.id.split("-")[1] ;
			$("#memberInfo").modal("toggle") ;
		}) ;
	}) ;
})