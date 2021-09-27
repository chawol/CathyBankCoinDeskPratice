此為面試用

起始化資料表位置在 src/main/resources/data.sql
使用方法:起來之後直接可以打API

1.http://localhost:8080/currencyName GET
說明:可以取得全部幣別對應名稱的資料
2.http://localhost:8080/currencyName/find POST 
說明:利用body的資料找到需要的那筆 全部條件皆為AND 串聯
Example Body :  {"id":1,"cyName": "USD"}
備註:目前可用選項有 cyChtName(幣別中文名),cyName(幣別縮寫),id
3.http://localhost:8080/currencyName/add POST
說明:新增一筆資料進去 可指定ID 也可不指定
Example Body :{"id": 3,"cyChtName": "歐元","cyName": "EUR"}
4.http://localhost:8080/currencyName/update PUT
說明:更新一筆資料 若指定ID 則刷新那筆資料 若不指定DB也沒相符的則會變新增
Example Body :{"id": 1,"cyChtName": "新台幣","cyName": "NTD"}
5.http://localhost:8080/currencyName?id={id} Delete
說明:刪除一筆資料 必須指定ID {id}
Example:http://localhost:8080/currencyName?id=3
會刪掉ID為3的那筆資料
6.http://localhost:8080/currencyName/getCoinDeskInfo
說明:取得轉換過的資料
7.http://localhost:8080/currencyName/getCoinDeskRaw
說明:取得原始的資料
