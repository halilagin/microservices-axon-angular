HOST=localhost
PORT=8039
#GET test
curl -d  '{"id":4, "price":221.0,"stock":3100, "description":"test-product2"}' -H "Content-Type: application/json" http://$HOST:$PORT/addProduct
