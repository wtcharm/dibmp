$(function(){
	$(pid).on("change",function(){
//		 console.log(this.value);
			if (this.value != "") {	// 有内容，需要进行ajax异步加载
				$.post("pages/back/admin/storage/listCity.action",{"pid":this.value},
						function(data){
					$("#cid option:gt(0)").remove() ;
					for (x = 0 ; x < data.length ; x ++) {
						$("#cid").append("<option value='"+data[x].cid+"'>"+data[x].title+"</option>") ;
					}
				},"json") ;
			} else {
				$("#cid option:gt(0)").remove() ;
			}
		}) ;
	$(wiid).on("change",function(){
//		 console.log(this.value);
			if (this.value != "") {	// 有内容，需要进行ajax异步加载
				$.post("pages/back/admin/storage/listWarehouse.action",{"wiid":this.value},
						function(data){
					$("#wid option:gt(0)").remove() ;
					for (x = 0 ; x < data.length ; x ++) {
						$("#wid").append("<option value='"+data[x].wid+"'>"+data[x].name+"</option>") ;
					}
				},"json") ;
			} else {
				$("#wid option:gt(0)").remove() ;
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
			"title" : {
				required : true
			} ,
			"pid" : {
				required : true 
			},
			"cid" : {
				required : true 
			},
			"wid" : {
				required : true 
			},
			"iid" : {
				required : true 
			},
			"note" : {
				required : true
			}
		}
	});
})