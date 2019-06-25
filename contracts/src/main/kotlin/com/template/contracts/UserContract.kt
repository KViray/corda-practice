package com.template.contracts

import com.template.states.TemplateState
import net.corda.core.contracts.*
import net.corda.core.transactions.LedgerTransaction

// ************
// * Contract *
// ************
@LegalProseReference(uri = "<prose_contract_uri>")
class UserContract : Contract {
    companion object {
        // Used to identify our contract when building a transaction.
        @JvmStatic
        val ID = "com.template.contracts.UserContract"
    }

    // A transaction is valid if the verify() function of the contract of all the transaction's input and output states
    // does not throw an exception.
    override fun verify(tx: LedgerTransaction) {
        val command = tx.commands.requireSingleCommand<UserContract.Commands>()
            when(command.value)
            {
                is Commands.Register -> requireThat {
                    {
                        "No inputs should be consumed when issuing an IOU" using (tx.inputs.isEmpty())
                        "Only one output state should be creating a record" using (tx.outputs.size == 1)
                        "Output must be a TokenState" using (tx.getOutput(0) is TemplateState)
                    }
                }
            }
        }
        // Verification logic goes here.


    // Used to indicate the transaction's intent.
    interface Commands : CommandData {
        class Register :TypeOnlyCommandData(), Commands
    }
}