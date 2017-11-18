<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="modal fade" id="warehouseInfo"  tabindex="-1" aria-labelledby="modalTitle" aria-hidden="true" data-keyboard="true">
	<div class="modal-dialog" style="width: 1000px">
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal" aria-hidden="true">&times;</button>
				<div class="form-group" id="didDiv">
					<strong><span class="glyphicon glyphicon-eye-open"></span>&nbsp;查看仓库信息</strong></h3>
				</div>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-3">
						<img src="upload/member/nophoto.png" style="width:200px;">
					</div>
					<div class="col-xs-8">
						<table class="table table-condensed" style="width:700px;">
							<tr>
								<td style="width:30%;"><strong>仓库名称：</strong></td>
								<td><span id="namew"></span></td>
							</tr>
							<tr>
								<td><strong>仓库地址：</strong></td>
								<td id="addressthistory"></td>
							</tr>
							<tr>
								<td><strong>库存类型：</strong></td>
								<td id="wiidl"></td>
							</tr>
							<tr>
								<td><strong>存储上限：</strong></td>
								<td id="max"></td>
							</tr>
							<tr>
								<td><strong>当前库存量：</strong></td>
								<td id="num"></td>
							</tr>
							<tr>
								<td><strong>备注信息：</strong></td>
								<td ><pre class="pre-scrollable" style="width:400px;height:210px;" id="notey"></pre></td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭窗口</button>
			</div>
		</div>
	</div>
</div>