$(function(){
	$(pid).on("change",function(){
		if (this.value != "") {	// 有内容，需要进行ajax异步加载
			$.post("pages/back/admin/goods/listCity.action",{"pid":this.value},
					function(data){
				$("#cid option:gt(0)").remove() ;
				for (x = 0 ; x < data.allCities.length ; x ++) {
					$("#cid").append("<option value='"+data.allCities[x].cid+"'>"+data.allCities[x].title+"</option>") ;
				}
			},"json") ;
		} else {
			$("#cid option:gt(0)").remove() ;
		}
	}) ;
	$("#myform").validate({
		debug : true, // 取消表单的提交操作
		submitHandler : function(form) {
			var title = $("#title").val();
			var pid = $("#pid").val();
			var cid = $("#cid").val();
			var note = $("#note").val();
			
			//form.submit(); // 提交表单
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
			"title" : {
				required : true
			}  ,
			"pid" : {
				required : true
			} ,
			"cid" : {
				required : true
			},
			"note" : {
				required : true
			}
		}
	});
})

