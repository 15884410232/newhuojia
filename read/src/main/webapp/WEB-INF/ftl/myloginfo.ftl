<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>个人信息</title>
	<meta name="renderer" content="webkit">	
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">	
	<meta name="apple-mobile-web-app-status-bar-style" content="black">	
	<meta name="apple-mobile-web-app-capable" content="yes">	
	<meta name="format-detection" content="telephone=no">	
	<link rel="stylesheet" type="text/css" href="assets/common/layui/css/layui.css" media="all">
	<link rel="stylesheet" type="text/css" href="assets/common/bootstrap/css/bootstrap.css" media="all">
	<link rel="stylesheet" type="text/css" href="assets/common/global.css" media="all">
	<link rel="stylesheet" type="text/css" href="assets/css/personal.css" media="all">
</head>
<body>
<section class="layui-larry-box">
	<div class="larry-personal">

		<div class="layui-inline">
			<div class="layui-form-mid layui-word-aux">生产日志</div>
		</div>
	</blockquote>
            
		    <div class="layui-tab-content larry-personal-body clearfix mylog-info-box">
		         <!-- 操作日志 -->
                <div id="pages" class="layui-tab-item layui-field-box layui-show">
                     <table class="layui-table table-hover" lay-even="" lay-skin="nob">
                         <thead>
                         <tr>
                             <th><input type="checkbox" id="selected-all"></th>
                             <th>ID</th>
                             <th>货架名称</th>
                             <th>货架位置</th>
                             <th>货架层数</th>
                             <th>操作</th>

                         </tr>
                         </thead>
                         <tbody id="tbody">

                         </tbody>
                     </table>
                     <div class="larry-table-page clearfix">
				          <div id="page" class="page"></div>
			         </div>
			    </div>

		    </div>
		</div>
	</div>
</section>
<script type="text/javascript" src="assets/common/layui/layui.js"></script>
<script type="text/javascript" src="assets/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript">
var pageCount="";
				$.ajax({
            			 type: "GET",
           				 url: "getRecordCount",
           				 data: '',
            			 dataType: "json",
            			 success: function(data){
 				pageCount=data.pageCount;
 						
                      }
       				  });

	layui.use(['jquery','layer','element','laypage'],function(){
	      window.jQuery = window.$ = layui.jquery;
	      window.layer = layui.layer;
          var element = layui.element(),
          laypage = layui.laypage;

          laypage({
					cont: $("#page"),
				pages:pageCount,   //总页数
                skip:true,  //是否开启跳页
                groups:5,   //连续显示分页数
                first:1,    //首页
                last:pageCount,    //尾页

                curr:1,     //当前页
					jump: function(obj, first) {
						//得到了当前页，用于向服务端请求对应数据
					var curr = obj.curr;
					$.ajax({
            			 type: "GET",
           				 url: "getHuojia",
           				 data: {'page':obj.curr},
            			 dataType: "json",
            			 success: function(data){
 						var html="";
 						for(index in data){
 							html+=('<tr>'
                             +'<td><input type="checkbox"></td>'
                             +'<td>'+data[index].huojiaId+'</td>'
                             +'<td>'+data[index].huojiaName+'</td>'
                             +'<td>'+data[index].huojiaAddress+'</td>'
                             +'<td>'+data[index].huojiaFloor+'</td>'
                             +'<td><a href="#">管理</a></td>'
                         	+'</tr>');
                         }
                         $("#tbody").html(html);
 						
                      }
       				  });
						
						
						if(!first) {
							//layer.msg('第 '+ obj.curr +' 页');
						}
					}
				});

         
    });
</script>
</body>
</html>