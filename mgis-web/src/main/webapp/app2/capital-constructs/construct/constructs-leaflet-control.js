L.Control.MGIS2ConstructsSelector = L.Control.extend({
	options: {
		position: 'topright',
		expand: false,
		className: 'leaflet-control-mgis-constructs',
	},
	initialize: function (options) {
		L.Util.setOptions(this, options);
		this._removeConstructListeners = new Array();
		this._removeListeners = new Array();
	},
	onAdd: function (map) {
		this._map = map;
		var className = this.options.className;
		var container = this._container = L.DomUtil.create('div', className + ' leaflet-control-layers leaflet-control');
		//if (!L.Browser.touch) {
		L.DomEvent.disableClickPropagation(container);
		L.DomEvent.on(container, 'wheel', L.DomEvent.stopPropagation);
		//} else {
		//L.DomEvent.on(container, 'click', L.DomEvent.stopPropagation);
		//}

		container.setAttribute("aria-haspopup", true);
		var landsList = this._constructsList = L.DomUtil.create("div", className + "-list", container);

		var toggleLink = this._toggleLink = L.DomUtil.create('a', className + '-toggle', container);
		toggleLink.href = "#/oks/maps/";
		toggleLink.title = "Constructs";
		toggleLink.text = " ";

		var me = this;
		L.DomEvent
			.on(container, 'mouseover', function (event) {
				this._toggleExpand(event, true && me._constructsList.innerHTML != "");
			}, this)
			.on(container, 'click', function (event) {
				this._toggleExpand(event, false);
			}, this)
			.on(toggleLink, 'click', function (event) {
				this._toggleExpand(event, me._constructsList.innerHTML != "");
			}, this)
		;


		this._update();
		this._toggleExpand({}, this.options.expand);

		return container;
	},
	onRemove: function (map) {
		this._removeConstructListeners.splice(0, this._removeConstructListeners.length);
		for (var i in this._removeListeners) {
			this._removeListeners[i]();
		}
		this._removeListeners.splice(0, this._removeListeners.length);
	},
	subscribeToRemove: function (listener) {
		this._removeListeners.push(listener);
	},
	_update: function () {
		if (!this._container) {
			return;
		}
		// TODO:
	},
	reloadConstructs: function (constructs) {
		this.clearConstructs();
		for (var i in constructs) {
			this.addConstruct(constructs[i]);
		}
	},
	addConstruct: function (construct) {
		var label = document.createElement('span');
		label.innerHTML = construct.cadastralnumber;
		var removeButton = document.createElement('button');
		removeButton.innerHTML = "---";
		removeButton.constructId = construct.id;
		removeButton.ConstructCadastralNumber = construct.cadastralnumber;
		L.DomEvent.on(removeButton, 'click', this._onRemoveButtonClick, this);
		var container = document.createElement("div");
		container.appendChild(label);
		container.appendChild(removeButton);
		this._constructsList.appendChild(container);
		this._toggleExpand({}, true);
	},
	removeConstruct: function (construct) {
		var buttons = this._constructsList.getElementsByTagName("button");
		for (var i in  buttons) {
			var button = buttons[i];
			if ((construct.id && construct.id == button.constructId) ||
				(construct.cadastralnumber && construct.cadastralnumber == button.constructCadastralNumber)) {
				var parent = button.parentNode;
				parent.parentNode.removeChild(parent);
				if (this._constructsList.innerHTML == "") {
					this._toggleExpand({}, false);
				}
				return true;
			}
		}
		return false;
	},
	clearConstructs: function () {
		for (var construct2 in this._constructsList.getElementsByTagName("button")) {
			this.removeConstruct({id: construct2.constructId, cadastralnumber: construct2.cadastralnumber});
		}
	},
	subscribeToRemoveConstruct: function (listener) {
		this._removeConstructListeners.push(listener);
	},
	unsubscribeFromRemoveConstruct: function (listener) {
		for (var i in this._removeConstructListeners) {
			if (this._removeConstructListeners[i] == listener) {
				this._removeConstructListeners.splice(i, 1);
			}
		}
	},
	_onRemoveButtonClick: function (event) {
		var target = event.currentTarget;
		var land = {id: target.constructId, cadastralnumber: target.constructCadastralNumber};
		for (var i in this._removeConstructListeners) {
			this._removeConstructListeners[i](event, land);
		}
	},
	_toggleExpand: function (event, expand) {
		if (
			(expand != undefined && expand) ||
			(expand == undefined && L.DomUtil.hasClass(this._container, 'leaflet-control-mgis-constructs-collapsed'))
		) {
			L.DomUtil.addClass(this._container, 'leaflet-control-mgis-constructs-expanded');
			L.DomUtil.removeClass(this._container, 'leaflet-control-mgis-constructs-collapsed');
		} else {
			L.DomUtil.addClass(this._container, 'leaflet-control-mgis-constructs-collapsed');
			L.DomUtil.removeClass(this._container, 'leaflet-control-mgis-constructs-expanded');
		}
	}
})
;
