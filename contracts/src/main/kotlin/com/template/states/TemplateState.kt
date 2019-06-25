package com.template.states

import com.template.contracts.TemplateContract
import net.corda.core.contracts.BelongsToContract
import net.corda.core.contracts.ContractState
import net.corda.core.contracts.LinearState
import net.corda.core.contracts.UniqueIdentifier
import net.corda.core.identity.AbstractParty

// *********
// * State *
// *********
@BelongsToContract(TemplateContract::class)
data class TemplateState(val firstName: String,
                         val lastName: String,
                         val age: Int,
                         val gender: String,
                         val address: String,
                         override val linearId: UniqueIdentifier,
                         override val participants: List<AbstractParty> = listOf()) : LinearState
