$(function(){
	$("#myform").validate({
		debug : true, // 取消表单的提交操作
		submitHandler : function(form) {
			form.submit(); // 提交表单
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
			"name" : {
				required : true,
				remote : {	    // 确保客户名字不重复
				url : "pages/back/admin/customer/add_check.action", // 后台处理程序
				type : "post", // 数据发送方式
				dataType : "html", // 接受数据格式
				data : { // 要传递的数据
					name : function() {
						return $("#name").val();
					}
				},
				dataFilter : function(data, type) {
					if (data.trim() == "true")
						return true;
					else
						return false;
				}
}
			} ,
			/*"name" : {
				required : true
			} ,*/
			"tid" : { 
				required : true 
			},
			"pid" : {
				required : true 
			},
			"cid" : {
				required : true 
			},
			"phone" : {
				required : true 
			},
			"address" : {
				required : true 
			},
			"lev" : {
				required : true 
			},
			"note" : {
				required : true
			}
		}
	});
	$(cid).on("change",function() {
		handleAddress() ;	// 处理地址 
	}) ;
	$(pid).on("change",function(){
		console.log("*******"+this.value);
		if (this.value != "") {	// 有内容，需要进行ajax异步加载
			$.post("pages/back/admin/customer/listCity.action",{"pid":this.value},
					function(data){
				$("#cid option:gt(0)").remove() ;
				for (x = 0 ; x < data.allCities.length ; x ++) {
					$("#cid").append("<option value='"+data.allCities[x].cid+"'>"+data.allCities[x].title+"</option>") ;
				}
			},"json") ;
			handleAddress() ;	// 处理地址 
		} else {
			$("#cid option:gt(0)").remove() ;
		}
	}) ;
})

function handleAddress() {	// 实现地址处理过程
	address = $("#address").val() ;	// 获得address原始内容
	ptitle = $("#pid option:selected").text() + " " ;
	ctitle = " " ;
	if ($("#cid option:selected").val() != "") {
		ctitle = $("#cid option:selected").text() + " " ;
	}
	adr = address.split(" ") ; 
	if (adr.length >= 3) {	// 都填写完了，现在要修改了
		str = ptitle + ctitle + adr[2] ;
		for (x = 2 ; x < adr.length ; x ++) {
			str += adr[x] + " " ; 
		}  
		$("#address").val(str) ;  
	} else {
		$("#address").val(ptitle + ctitle) ;  
	}
}