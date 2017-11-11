$(function(){
	
	 said = $("#said").html();
	 $.post("pages/back/admin/manage/storage_shop.action",{"said":said},function(data){
		 //console.log(data.details+"长度"+data.details.length);
		 detail = data.details;
		 for(a =0;a< detail.length ;a ++){
			 var row= "<tr id='dettr-"+detail[a].gid+"' class='text-success'>"+
				"<td>"+detail[a].gid+"</td>"+
				"<td id='name-"+detail[a].gid+"'>"+detail[a].name+"</td>"+
				"<td id='num-"+detail[a].gid+"'>"+detail[a].num+"</td>"+
				"<td id='price-"+detail[a].gid+"'>"+detail[a].price+"</td>"+
				"<td id='weight-"+detail[a].gid+"'>"+detail[a].weight+"</td>"+
				"<td> <button id='access-"+detail[a].gid+"' class='btn btn-primary btn-xs'>"+
					"<span class='glyphicon glyphicon-edit'></span>&nbsp;允许入库</button>"+
					"<button id='denied-"+detail[a].gid+"' class='btn btn-danger btn-xs'>"+
					"<span class='glyphicon glyphicon-edit'></span>&nbsp;拒绝入库</button>"+
					"</td>"+
				"</tr>";
			 $("#tbodyTite").append($(row));
		 }
		 $("button[id^=access-]").each(function(){
				$(this).on("click",function(){
					sdid = this.id.split("-")[1] ;
					name= $("#name-"+sdid).html();
					num =$("#num-"+sdid).html();
					price= $("#price-"+sdid).html();
					weight= $("#weight-"+sdid).html();
					$.post("pages/back/admin/manage/record_shop.action",
						{"gid":sdid,"name":name,"num":num,"price":price,"weight":weight},
						function(data){
						console.log($("#dettr-"+sdid).html());
					});
					$("#dettr-"+sdid).remove();
					
					operateAlert(true,"商品成功入库存储！","商品入库失败！") ;
				}) ;
			}) ;
		
		 $("button[id^=denied-]").each(function(){  //record_shop_error
				$(this).on("click",function(){
					sdid = this.id.split("-")[1] ;
					name= $("#name-"+sdid).html();
					num =$("#num-"+sdid).html();
					price= $("#price-"+sdid).html();
					weight= $("#weight-"+sdid).html();
					$.post("pages/back/admin/manage/record_shop_error.action",
						{"gid":sdid,"name":name,"num":num,"price":price,"weight":weight},
						function(data){
						console.log(data);
					});
					$("#dettr-"+sdid).remove();
					operateAlert(false,"商品成功入库存储！","商品入库失败！") ;
				}) ;
			}) ;
		 
	 });
	 
})