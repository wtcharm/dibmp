loadFlag = false ;
$(function() {
	$("button[id^=storage-]").each(function(){
		$(this).on("click",function(){
			gid = this.id.split("-")[1] ;
		    $.post("pages/back/admin/goods/detailSstorage.action",{"gid":gid},function(data){
		    	var row = "<tr class='text-primary'>" +
		    			"<td class='text-left'>"+data.address+"</td>" +
		    			"<td class='text-center'>"+data.cityName+"</td>" +
		    			"<td class='text-center'>"+data.proviceName+"</td>" +
		    			"<td class='text-center'>"+data.stornum+"</td></tr>";
		    	$("#details").append($(row)) ;
		    },"json");
			$("#goodsRecordInfo").modal("toggle") ; 
		}) ;
	}) ;
	
	$('#storageDetails').on('show.bs.collapse', function () {
		if (loadFlag == false) {
			loadFlag = true ;
			$("span[id^=mid-]").each(function(){
				gid = this.id.split("-")[1];
				$.post("pages/back/admin/goods/storageDetails.action",{"gid":gid},function(data){
						var row = "<tr class='text-primary'>" +
								"<td class='text-center'>"+data.lastin+"</td>" +
								"<td class='text-left'>"+data.address+"</td>" +
								"<td class='text-center'><span id='mid-${GoodsDetails.name}' style='cursor:pointer;'>"+data.appmid+"</span></td>" +
								"<td class='text-center'><span id='mid-${GoodsDetails.name}' style='cursor:pointer;'>"+data.admin+"</span></td>" +
								"<td class='text-center'>"+data.stornum+"</td>" +
								"</tr>";
						$("#mytables").append($(row)) ;
				},"json")
				operateAlert(true,"商品库存信息加载成功！","商品库存信息加载失败！") ;
				$(this).on("click",function(){
					mid = this.id.split("-")[1] ;
					alert(mid);
					$("#memberInfo").modal("toggle") ;
				}) ;
			}) ;
		}
	}) ;
})