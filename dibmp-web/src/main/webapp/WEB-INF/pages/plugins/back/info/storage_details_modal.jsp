<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="modal fade" id="storageDetailsInfo"  tabindex="-1" aria-labelledby="modalTitle" aria-hidden="true" data-keyboard="true">
	<div class="modal-dialog" style="width: 1000px">
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal" aria-hidden="true">&times;</button>
				<div class="form-group" id="didDiv">
					<strong><span class="glyphicon glyphicon-eye-open"></span>&nbsp;查看入库单详情</strong></h3>
				</div>
			</div>
			<div class="modal-body">
				<div>
					<table class="table table-striped table-bordered table-hover">
						<tr> 
							<td style="width:150px;"><strong>入库标题：</strong></td>
							<td id="titles"></td>
						</tr>
						<tr>
							<td><strong >存入仓库名称：</strong></td>
							<td id="name"></td>
						</tr>
						<tr>
							<td><strong >仓库类型：</strong></td>
							<td id="wiids"></td>
						</tr>
						<tr>
							<td><strong >入库商品总价：</strong></td>
							<td id="nums"></td>
						</tr>
						<tr>
							<td><strong>备注信息：</strong></td> 
							<td id="notes"><pre class="pre-scrollable" style="width:800px;height:60px;" ></pre></td>
						</tr>
					</table>
				</div>
				<div>
					<table class="table table-condensed table-bordered table-hover" id="detailsTab">
						<thead>
							<tr>
								<th class="text-center" style="width:10%;">商品编号</th> 
								<th class="text-left" style="width:40%;">商品名称</th> 
								<th class="text-center" style="width:10%;">入库数量</th>
								<th class="text-center" style="width:15%;">商品单价（￥）</th>
								<th class="text-center" style="width:15%;">单位重量（g）</th>
								<th class="text-center" style="width:10%;">总价（￥）</th>
							</tr>
						</thead>
						<tbody id="shop">
							
						</tbody>
					</table>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭窗口</button>
			</div>
		</div>
	</div>
</div>