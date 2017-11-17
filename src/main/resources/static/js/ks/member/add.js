/**
 * 新增-js
 */
var vm = new Vue({
	el:'#dpLTE',
	data: {
		member: {
			id: 0
		}
	},
	methods : {
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
		    $.SaveForm({
		    	url: '../../ks/member/save?_' + $.now(),
		    	param: vm.member,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	}
})
