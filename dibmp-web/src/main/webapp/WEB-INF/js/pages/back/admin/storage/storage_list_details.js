temp_did = 1 ;
var url = window.location.search; 
//alert(url.length); 
//alert(url.lastIndexOf('=')); 
var said = url.substring(url.lastIndexOf('=')+1, url.length);
//console.log("said"+said);
$(function(){
	$(addbut).on("click",function(){
		// 通过ajax保存一行新的数据，而后把详情id取得，替换掉如下的id设置
		addDetails("temp" + temp_did ++) ; // 设置一个临时 id信息
	}) ;
	$("button[id^=save-]").each(function(){
		$(this).on("click",function(){
			did = this.id.split("-") [1] ;
			saveDetails(did) ;
		}) ;
	}) ;
	$("button[id^=remove-]").each(function(){
		$(this).on("click",function(){
			did = this.id.split("-") [1] ;
			deleteDetails(did) ;
		}) ;
	}) ;
})
function addDetails(tdid) {
	trInfo = 	$("<tr id='dettr-"+tdid+"' class='text-danger'>" +
				"	<td><input type='text' id='gid-"+tdid+"' value='0'/></td>" + 
				"	<td><input type='text' id='name-"+tdid+"' value='等待查询...' size='50' disabled="+'disabled'+"/></td>" +
				"	<td><input type='text' id='amount-"+tdid+"' value='0' maxlength='8' size='8'/></td>" +
				"	<td><input type='text' id='price-"+tdid+"' value='0.0' maxlength='8' size='8' disabled="+'disabled'+"/></td>" +
				"	<td><input type='text' id='weight-"+tdid+"' value='0' maxlength='8' size='8' disabled="+'disabled'+"/></td>" +
				"	<td>" +
				"		<button id='save-"+tdid+"' class='btn btn-primary btn-xs'>" +
				"			<span class='glyphicon glyphicon-edit'></span>&nbsp;保存</button>" +
				"		<button id='remove-"+tdid+"' class='btn btn-danger btn-xs'>" +
				"			<span class='glyphicon glyphicon-edit'></span>&nbsp;移除</button>" +
				"	</td>" +
				"</tr>") ;

	//去掉字符串头尾空格     
    function valueTrim(str) {     
        return str.replace(/(^\s*)|(\s*$)/g, "");     
    } 
	function isNumber(numberValue){  
	    //定义正则表达式部分      
	    var reg1 = /^[1-9]{0,}$/;  
	    var reg2 = /^[1-9]{1}[0-9]{0,}$/;
	    //alert(numberValue);  
	    if(numberValue ==null || numberValue.length==0){  
	        return false;  
	    }  
	    numberValue = valueTrim(numberValue);  
	    //判断当数字只有1位时  
	    if(numberValue.length<2){  
	        return reg1.test(numberValue);  
	    }
	    return reg2.test(numberValue);;  
	} 
	
	$(detailsTab).append(trInfo) ;
	$("#gid-"+tdid).on("blur",function(){
		if(!isNumber(this.value)){
			operateAlert(false,"入库商品信息添加成功！","数据输入有误！") ;
		}else{
			$.post("pages/back/admin/storage/findGoods.action",{"gid":this.value},
					function(data){
				if(data == false){
					$("#name-"+tdid).val("");
					$("#amount-"+tdid).val("");
					$("#price-"+tdid).val("");
					$("#weight-"+tdid).val("");
					operateAlert(false,"入库商品信息添加成功！","没有此商品！") ;
				}else{
					$("#name-"+tdid).val(data.name);
					$("#amount-"+tdid).val(data.stornum);
					$("#price-"+tdid).val(data.price);
					$("#weight-"+tdid).val(data.weight);
				}
			},"json") ;
		}
	});
	$("#save-" + tdid).on("click",function(){
		gid = $("#gid-"+tdid).val();
		
		if(!isNumber(gid)){
			
			operateAlert(false,"入库商品信息添加成功！","数据输入有误！") ;
		}else{
			$.post("pages/back/admin/storage/findGoods.action",{"gid":gid},
					function(data){
				if(data==false){
					operateAlert(false,"入库商品信息添加成功！","数据输入有误！") ;
				}else{
					name = $("#name-"+tdid).val();
					price = $("#price-"+tdid).val();
					weight = $("#weight-"+tdid).val();
					amount = $("#amount-"+tdid).val();
					if(!isNumber(amount)){
						operateAlert(false,"入库商品信息添加成功！","数据输入有误！") ;
					}else{
						saveDetails(tdid,gid,name,price,weight,amount) ;
					}
				}
			},"json") ;
		}
	}) ;
	$("#remove-" + tdid).on("click",function(){
		gid = $("#gid-"+tdid).val();
		deleteDetails(tdid,gid) ;
	}) ;
	function saveDetails(did,gid,name,price,weight,amount) {
		console.log("【增加】详情编号：" + did) ;
		// 需要进行数据验证，而后再进行ajax提交处理，当提交成功后应该获取最后一次增长ID信息，替换掉原始的临时id
		$.post("pages/back/admin/storage/saveGoods.action",
				{"gid":gid,"name":name,"price":price,"weight":weight,"num":amount,"said":said},
				function(data){
			if(data.trim()=="true"){
				$("#dettr-" + did).attr("class","text-success") ;
				operateAlert(true,"入库商品信息添加成功！","入库商品信息添加失败！") ;
			}else{
				operateAlert(false,"入库商品信息添加成功！","入库商品信息添加失败！") ;
			}
		},"text") ;
		
	}
	function deleteDetails(did,gid) {
		console.log("【删除】详情编号：" + did) ;
		// ajax移除信息，而后删除表格；
		$.post("pages/back/admin/storage/removeGoods.action",
				{"gid":gid,"said":said},
				function(data){
			if(data.trim()=="true"){
				$("#dettr-" + did).remove() ;
				operateAlert(true,"入库商品信息删除成功！","入库商品信息删除失败！") ;
			}else{
				operateAlert(false,"入库商品信息删除成功！","入库商品信息删除失败！") ;
			}
		},"text") ;
	}
}