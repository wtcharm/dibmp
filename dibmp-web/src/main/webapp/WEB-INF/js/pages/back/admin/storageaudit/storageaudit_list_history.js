$(function(){
	$("span[id^=mid_]").each(function(){
		$(this).on("click",function(){
			mid = this.id.split("_")[1] ;
			$("#memberInfo").modal("toggle") ;
			console.log(mid)
			$.post("pages/back/admin/storageaudit/levelanddeptby_member.action",{"mid":mid},function(data){
				//console.log(data.sum)
				console.log(data);
				$("#names").html(data.allMember.name);
				$("#dename").html(data.allLevle.title);
				$("#levename").html(data.allDept.dname);
				$("#address").html(data.allMember.phone);
				$("#note").html(data.allMember.note);
			})


		}) ;
	}) ;
	$("span[id^=sid-]").each(function(){
		$(this).on("click",function(){
			sid = this.id.split("-")[1] ;
			status=5;
			$("#storageDetailsInfo").modal("toggle") ;
			console.log(sid);
			$.post("pages/back/admin/storageaudit/according_to.action",{"said":sid,"status":status},function(data){
				console.log(data)
				$("#titles").html(data.apply.title);
				$("#name").html(data.allWarehosut.name);
				$("#wiids").html(data.allWarehosut.address);
				$("#nums").html("￥"+data.sum);
				$("#notes").html(data.apply.note);
				console.log(data.applyDetails[0].sadid)
				apply= data.applyDetails;
				var shop = $("#shop");
				shop.empty();
				for(a=0;a<apply.length;a++){
					var rw="<tr class='text-primary'>" +
					"<td class='text-center'>"+apply[a].sadid+"</td>"+
					"<td class='text-left'>"+apply[a].name+"</td>"+
					"<td class='text-center'>"+apply[a].num+"</td>"+
					"<td class='text-center'>"+apply[a].price+"</td>"+
					"<td class='text-center'>"+apply[a].weight+"</td>"+
					"<td class='text-center'>"+(apply[a].price*apply[a].num).toFixed(2)+"</td>"+
					"</tr>" ;
					shop.append($(rw));

				}
			})

		}) ;
	}) ;
	$("span[id^=wid-]").each(function(){
		$(this).on("click",function(){
			wid = this.id.split("-")[1] ;
			$("#warehouseInfo").modal("toggle") ;
			console.log(wid);
			$.post("pages/back/admin/storageaudit/wareHouseAndApply.action",{"wid":wid},function(data){
				//console.log(data);
				$("#namew").html(data.allWareHouse.name);
				$("#addressthistory").html(data.allWareHouse.address);
				$("#wiidl").html(data.allWitem.title);
				$("#max").html(data.allWareHouse.maximum);
				$("#num").html(data.allWareHouse.currnum);
				$("#notey").html(data.allWareHouse.note);
			});
		}) ;
	}) ;
}) ;
