<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/pages/plugins/back/back_header.jsp"/>
<script type="text/javascript" src="js/pages/back/admin/storage/storage_list_details.js"></script>
<script type="text/javascript" src="js/split_page.js"></script>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<!-- 导入头部标题栏内容 -->
		<jsp:include page="/WEB-INF/pages/plugins/back/include_title_head.jsp" />
		<!-- 导入左边菜单项 -->
		<jsp:include page="/WEB-INF/pages/plugins/back/include_menu_item.jsp">
			<jsp:param name="mi" value="3"/>
			<jsp:param name="msi" value="32"/>
		</jsp:include>
		<div class="content-wrapper text-left">
		<div class="panel panel-info">
			<div class="panel-heading">
				<strong><span class="glyphicon glyphicon-list"></span>&nbsp;我的入库申请单</strong>
			</div>
			<div class="panel-body">
				<div>
					<table class="table table-striped table-bordered table-hover">
						<tr> 
							<td style="width:150px;"><strong>入库标题：</strong></td>
							<td>${storageApply.title}</td>
						</tr>
						<tr>
							<td><strong>存入仓库名称：</strong></td>
							<td>${warehouses.name}</td>
						</tr>
						<tr>
							<td><strong>仓库类型：</strong></td>
							<td>${witems.title}</td>
						</tr>
						<tr>
							<td><strong>备注信息：</strong></td>
							<td>${storageApply.note}</td>
						</tr>
						<c:if test="${storageApply.status==0||storageApply.status==3}">
						<tr>
							<td><strong>入库操作：</strong></td>
							<td>
								<button id="addbut" class="btn btn-danger btn-xs" >
										<span class="glyphicon glyphicon-edit"></span>&nbsp;追加商品</button>
							</td>
						</tr>
						</c:if>
					</table>
				</div>
				<div>
					<table class="table table-condensed" id="detailsTab">
						<thead>
							<tr>
								<th class="text-left" style="width:10%;">商品编号</th> 
								<th class="text-left" style="width:40%;">商品名称</th> 
								<th class="text-left" style="width:10%;">入库数量</th>
								<th class="text-left" style="width:10%;">商品单价（元）</th>
								<th class="text-left" style="width:10%;">单位重量（g）</th>
								<c:if test="${storageApply.status==0||storageApply.status==3}">
								<th class="text-left" style="width:20%;">操作</th>
								</c:if>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${storageApplyDetails}" var="storageApplyDetails">
							<tr id="dettr-${storageApplyDetails.sadid}" class="text-success">
								<td><input type="text" id="gid-${storageApplyDetails.sadid}" value="${storageApplyDetails.gid}"/></td>
								<td><input type="text" id="name-${storageApplyDetails.sadid}" value="${storageApplyDetails.name}" size="50"/></td>
								<td><input type="text" id="amount-${storageApplyDetails.sadid}" value="${storageApplyDetails.num}" maxlength="8" size="8"/></td>
								<td><input type="text" id="price-${storageApplyDetails.sadid}" value="${storageApplyDetails.price}" maxlength="8" size="8"/></td>
								<td><input type="text" id="weight-${storageApplyDetails.sadid}" value="${storageApplyDetails.weight}" maxlength="8" size="8"/></td>
								<c:if test="${storageApply.status==0||storageApply.status==3}">
								<td>
									<button id="save-${storageApplyDetails.sadid}" class="btn btn-primary btn-xs">
										<span class="glyphicon glyphicon-edit"></span>&nbsp;保存</button>
									<button id="remove-${storageApplyDetails.sadid}" class="btn btn-danger btn-xs">
										<span class="glyphicon glyphicon-edit"></span>&nbsp;移除</button>
								</td>
								</c:if>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div class="panel-footer" style="height:100px;">
				<jsp:include page="/WEB-INF/pages/plugins/include_alert.jsp"/>
			</div>
		</div>
		</div>
		<!-- 导入公司尾部认证信息 -->
		<jsp:include page="/WEB-INF/pages/plugins/back/include_title_foot.jsp" />
		<!-- 导入右边工具设置栏 -->
		<jsp:include page="/WEB-INF/pages/plugins/back/include_menu_sidebar.jsp" />
		<div class="control-sidebar-bg"></div>
	</div>
	<jsp:include page="/WEB-INF/pages/plugins/back/info/member_info_modal.jsp"/>
	<jsp:include page="/WEB-INF/pages/plugins/back/info/member_dept_list_modal.jsp"/>
	<jsp:include page="/WEB-INF/pages/plugins/back/include_javascript_foot.jsp" />
<jsp:include page="/WEB-INF/pages/plugins/back/back_footer.jsp"/>
