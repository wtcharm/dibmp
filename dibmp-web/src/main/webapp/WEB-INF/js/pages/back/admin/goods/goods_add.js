
$(function(){
	$(wiid).on("change",function(){
	 console.log(this.value);
			if (this.value != "") {	// 有内容，需要进行ajax异步加载
				$.post("pages/back/admin/goods/listSubtype.action",{"wiid":this.value},
						function(data){
					$("#stid option:gt(0)").remove() ;
					for (x = 0 ; x < data.length ; x ++) {
						$("#stid").append("<option value='"+data[x].stid+"'>"+data[x].title+"</option>") ;
					}
				},"json") ;
			} else {
				$("#stid option:gt(0)").remove() ;
			}
		}) ;
	
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
				remote : {
					url : "pages/back/admin/goods/findByName.action", // 后台处理程序
					type : "post", // 数据发送方式
					dataType : "text", // 接受数据格式
					data : { // 要传递的数据
						"name" : function() {
							return $("#name").val();
						}
					},
					dataFilter : function(data, type) {
						if (data.trim() == "false"){//不重复
							$("#nameMsg").empty();
							return true;
						}else{
							$("#name").fadeOut(1,function() {
								$("#name").fadeIn(1, function() {
									$("#nameMsg").empty();
									$("#nameMsg").attr("class","form-group has-error").append("<lable class='"+"form-group has-error text-danger"+"'>商品名称已存在</lable>");
								});
							})
							return false;
						}
					}
				}
			} ,
			"wiid" : {
				required : true
			},
			"stid" : {
				required : true 
			},
			"price" : {
				required : true ,
				number : true 
			},
			"weight" : {
				required : true ,
				number : true 
			},
			"pic" : {
//				required : true ,
				accept : ["jpg","png","gif","bmp"]
			},
			"note" : {
				required : true
			}
		}
	});
})