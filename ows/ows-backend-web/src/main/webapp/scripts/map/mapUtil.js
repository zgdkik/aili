

	
	
	
	// 复杂的自定义覆盖物
	function ComplexNetworkOverlay(point, text){
      this._point = point;
      this._text = text;
     
    }
    ComplexNetworkOverlay.prototype = new BMap.Overlay();
    //初始化 调用方法时调用
   	ComplexNetworkOverlay.prototype.initialize = function(map){
      var color;
      var border;
      var backgroundPosition;
      //自车样式
	  color='#EE5D5B';
	  border="1px solid #BC3B3A";
	  backgroundPosition="-0px -0px";
      this._map = map;
      var div = this._div = document.createElement("div");
      div.style.position = "absolute";
      div.style.zIndex = BMap.Overlay.getZIndex(this._point.lat);
   	  div.style.backgroundColor = color;
      div.style.border = border;
      div.style.color = "white";
      div.style.height = "18px";
      div.style.padding = "2px";
      div.style.lineHeight = "18px";
      div.style.whiteSpace = "nowrap";
      div.style.MozUserSelect = "none";
      div.style.fontSize = "12px"
      var span = this._span = document.createElement("span");
      div.appendChild(span);
      span.appendChild(document.createTextNode(this._text));      
      var that = this;
		//添加背景 ..箭头等
      var arrow = this._arrow = document.createElement("div");
      arrow.style.background = "url("+base+"/images/map/label.png) no-repeat";
      arrow.style.position = "absolute";
      arrow.style.width = "10px";
      arrow.style.height = "10px";
      arrow.style.top = "22px";
      arrow.style.left = "10px";
      arrow.style.backgroundPosition = backgroundPosition;
      arrow.style.overflow = "hidden";
      div.appendChild(arrow);
      map.getPanes().labelPane.appendChild(div);
      return div;
    }
    ComplexNetworkOverlay.prototype.draw = function(){
      var map = this._map;
      var pixel = map.pointToOverlayPixel(this._point);
      this._div.style.left = pixel.x - parseInt(this._arrow.style.left) + "px";
      this._div.style.top  = pixel.y - 30 + "px";
    }
    ComplexNetworkOverlay.prototype.addEventListener = function(event,fun){
    	this._div['on'+event] = fun;
	}
    
    function myZoomControl(color,left, top) {
		// 默认停靠位置和偏移量
		this.defaultAnchor = BMAP_ANCHOR_TOP_RIGHT;
		this.defaultOffset = new BMap.Size(left, top);
		this.color = color;
	}
	// 通过JavaScript的prototype属性继承于BMap.Control
	myZoomControl.prototype = new BMap.Control();
	// 自定义控件必须实现自己的initialize方法,并且将控件的DOM元素返回
	// 在本方法中创建个div元素作为控件的容器,并将其添加到地图容器中
	myZoomControl.prototype.initialize = function(smallMap) {
		// 创建一个DOM元素
		var div = document.createElement("div");
		// 添加文字说明
		// div.appendChild(document.createTextNode(this.text));
		// 设置样式
		div.id = "myzoom";
		div.style.cursor = "pointer";
		div.style.border = "1px solid gray";
		div.style.backgroundColor = this.color;
		div.style.padding = "5px 10px 5px 10px";
		// 绑定事件,点击一次放大两级
		// div.onclick = this.func;
		// 添加DOM元素到地图中
		smallMap.getContainer().appendChild(div);
		// 将DOM元素返回
		return div;
	}
	
	function myZoomTopLeft(color,left, top) {
		// 默认停靠位置和偏移量
		this.defaultAnchor = BMAP_ANCHOR_TOP_LEFT;
		this.defaultOffset = new BMap.Size(left, top);
		this.color = color;
	}
	// 通过JavaScript的prototype属性继承于BMap.Control
	myZoomTopLeft.prototype = new BMap.Control();
	// 自定义控件必须实现自己的initialize方法,并且将控件的DOM元素返回
	// 在本方法中创建个div元素作为控件的容器,并将其添加到地图容器中
	myZoomTopLeft.prototype.initialize = function(smallMap) {
		// 创建一个DOM元素
		var div = document.createElement("div");
		// 添加文字说明
		// div.appendChild(document.createTextNode(this.text));
		// 设置样式
		div.id = "myzoom2";
		div.style.cursor = "pointer";
		div.style.backgroundColor = this.color;
		div.style.background="rgba(1,1,1,0)";
		div.style.padding = "5px 10px 5px 10px";
		
		// 绑定事件,点击一次放大两级
		// div.onclick = this.func;
		// 添加DOM元素到地图中
		smallMap.getContainer().appendChild(div);
		// 将DOM元素返回
		return div;
	}
