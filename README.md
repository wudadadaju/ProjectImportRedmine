# ProjectImportRedmine
此项目是因解决project文件导入困难问题而生

使用此项目需要先将project另存为Excel，之后配置相应的路径、数据库连接，运行main方法即可完成导入。

Excel格式如下：
<table border="1">
<tr>
  <td>标识号</td>
  <td>活动</td>
  <td>任务模式</td>
  <td>名称</td>
  <td>工期</td>
  <td>开始时间</td>
  <td>完成时间</td>
  <td>前置任务</td>
  <td>大纲级别</td>
  <td>备注</td>
  <td>分配给</td>
  <td>目标项目</td>
</tr>
<tr>
  <td>1</td>
  <td>是</td>
  <td>手动计划</td>
  <td>系统开发准备</td>
  <td>48 days</td>
  <td>17/11/1 8:00</td>
  <td>18/1/5 17:00</td>
  <td></td>
  <td>1</td>
  <td></td>
  <td></td>
  <td></td>
  </tr>
<tr>
  <td>2</td>
  <td>是</td>
  <td>手动计划</td>
  <td>需求分析</td>
  <td>44 days</td>
  <td>17/11/1 8:00</td>
  <td>17/12/31 17:00</td>
  <td></td>
  <td>2</td>
  <td></td>
  <td></td>
  <td></td>
</tr>
<tr>
  <td>3</td>
  <td>是</td>
  <td>手动计划</td>
  <td>原型系统需求文档准备</td>
  <td>44 days</td>
  <td>17/11/1 8:00</td>
  <td>17/12/31 17:00</td>
  <td></td>
  <td>3</td>
  <td></td>
  <td></td>
  <td></td>
</tr>
</table>

