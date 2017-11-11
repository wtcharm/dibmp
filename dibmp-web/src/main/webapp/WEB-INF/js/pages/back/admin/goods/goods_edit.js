$(function(){
	$(wiid).on("change",function(){
//		 console.log(this.value);
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
				required : true
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
//				digits : true 
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