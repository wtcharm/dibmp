<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/pages/plugins/back/back_header.jsp"/>
<script type="text/javascript" src="js/pages/back/admin/distribution/distribution_goods_list.js"></script>
<script type="text/javascript" src="js/split_page.js"></script>
<%!
	public static final String DISTRIBUTION_ADD_URL = "pages/back/admin/goods/add_pre.action" ; 
%>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<!-- 导入头部标题栏内容 -->
		<jsp:include page="/WEB-INF/pages/plugins/back/include_title_head.jsp" />
		<!-- 导入左边菜单项 -->
		<jsp:include page="/WEB-INF/pages/plugins/back/include_menu_item.jsp">
			<jsp:param name="mi" value="7"/>
			<jsp:param name="msi" value="71"/>
		</jsp:include>
		<div class="content-wrapper text-left">
		<div class="panel panel-info">
			<div class="panel-heading">
				<strong><span class="glyphicon glyphicon-list"></span>&nbsp;待出库清单</strong>
			</div>
			<div class="panel-body">
				<div>
				<c:forEach items="${customer}" var="customer">
					<table class="table table-striped table-bordered table-hover">
						<tr> 
							<td style="width:150px;"><strong>客户姓名：</strong></td>
							<td>${customer.name}</td>
						</tr>
						<tr>
							<td><strong>客户联系电话：</strong></td>
							<td>${customer.phone}</td>
						</tr>
						<tr>
							<td><strong>客户重要性：</strong></td>
							<td><span class="text-danger">${customer.levelTitle}</span></td>
						</tr>
						<tr>
							<td><strong>客户备注信息：</strong></td> 
							<td>
								<pre class="pre-scrollable" style="width:900px;height:60px;">${customer.note}</pre>
							</td>
						</tr>
					</table>
					</c:forEach>
				</div>
				<div>
					<table class="table table-condensed">
						<thead>
							<tr>
								<th class="text-center" style="width:5%">
									<input type="checkbox" id="selectAll">
								</th>
								<th class="text-center" style="width:5%"><strong>图片</strong></th>
								<th class="text-left" style="width:50%"><strong>商品</strong></th>
								<th class="text-center" style="width:10%"><strong>单价</strong></th>
								<th class="text-center" style="width:10%"><strong>库存</strong></th>
								<th class="text-center" style="width:20%"><strong>数量</strong></th>
								<th class="text-center" style="width:10%"><strong>操作</strong></th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${allRedisGoods}" var="redisGoods">
							<tr id="goods-${redisGoods.gid}">
								<td class="text-center">
									<input type="checkbox" id="gid" name="gid" value="${redisGoods.gid}">
								</td>
								<td class="text-center"><img src="upload/goods/nophoto.png" style="width:30px;"></td>
								<td class="text-left"><span id="name-${redisGoods.gid}">${redisGoods.name}</span></td>
								<td class="text-center"><span id="price-${redisGoods.gid}">${redisGoods.price}</span></td>
								<td class="text-center"><span id="stornum-${redisGoods.gid}">${redisGoods.stornum}</span></td>
								<td class="text-center">
									<button class="btn btn-primary" id="sub-${redisGoods.gid}">-</button>
									<input type="text" id="amount-${redisGoods.gid}" name="amount-${redisGoods.gid}" class="shopcar-form-control" size="4" maxlength="4" value="${redisGoods.num}">
									<button class="btn btn-primary" id="add-${redisGoods.gid}">+</button> 
								</td>
								<td class="text-center"><button class="btn btn-primary" id="updateBtn-${redisGoods.gid}">修改</button></td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="text-right">
					总价￥<span id="allPrice" class="text-danger h2">78.9</span>
				</div>
				<div>
					<button class="btn btn-primary" id="editBtn"><span class="glyphicon glyphicon-pencil"></span>&nbsp;修改数量</button>
					<button class="btn btn-danger" id="rmBtn"><span class="glyphicon glyphicon-remove"></span>&nbsp;移出清单</button>
					<button class="btn btn-success" id="createBtn"><span class="glyphicon glyphicon-file"></span>&nbsp;创建出库申请单</button>
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
	<jsp:include page="/WEB-INF/pages/plugins/back/info/goods_storage_modal.jsp"/>
	<jsp:include page="/WEB-INF/pages/plugins/back/include_javascript_foot.jsp" />
<jsp:include page="/WEB-INF/pages/plugins/back/back_footer.jsp"/>
