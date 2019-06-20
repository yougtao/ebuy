## 根据ID查询商品的详情
url: /item/{itemId}
参数: itemId (携带在url中的)
返回: json格式数据


{"total":934,
    "rows":[
        {"id":9527, "title":"九五二期","cid":58,....},
        {"id":9527, "title":"九五二期","cid":58,....},
        {"id":9527, "title":"九五二期","cid":58,....},
        {"id":9527, "title":"九五二期","cid":58,....}
    ]
}


item/cat/list

controller: TreeNode queryCat  RequestParam(name="id") integer parentId


## 项目结构
* ebuy-parent (pom工程)
	* ebuy-common 
	* ebuy-manager	(pom工程)
		* ebuy-manager-pojo
		* ebuy-manager-dao
		* ebuy-manager-interface
		* ebuy-manager-service (war包,方便部署)
	* ebuy-manager-web
	* ebuy-web	(web工程)
	* ebuy-content (pom聚合工程)
		* ebuy-content-interface
		* ebuy-content-service (war包,方便部署)
	
	
	
### 各个服务端口
* mysql服务器 jdbc:mysql://192.168.22.137/ebuy
	
* dubbo 20196
* zookeeper服务器 zookeeper://192.168.22.138:2181

* FastDFS
	* tracker.server 192.168.22.137:22122
	* http.tracker_http_port 192.168.22.137:8888
	
* redis 集群
	* redis-server 192.168.22.129:9001
	* redis-server 192.168.22.129:9002
	* redis-server 192.168.22.129:9003
	* redis-server 192.168.22.129:9004
	* redis-server 192.168.22.129:9005
	* redis-server 192.168.22.129:9006
	

* ebuy-manager localhost:8083
	
* ebuy-manager-web locahost:8082
	
* ebuy-content localhost:8081
 
* ebuy-web localhost:8080










