Ext.define(App.path('model.TxnSeach'), {
    extend: 'Ext.data.Model',
    fields:[
        'txnID', 'agentID','serviceName','txnDate','seller_AgentID','status'
    ]
});
