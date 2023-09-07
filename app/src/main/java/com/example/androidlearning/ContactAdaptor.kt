package com.example.androidlearning

import android.content.ContentProviderOperation
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlearning.databinding.ContactListBinding

class ContactAdaptor(private val contactList: List<ContactModel>): RecyclerView.Adapter<ContactViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ContactViewHolder(ContactListBinding.inflate(layoutInflater,parent,false))
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.setContact(contactList[position])
        holder.updateContact(contactList[position])
    }

    override fun getItemCount(): Int {
       return contactList.size
    }
}

class ContactViewHolder(private val binding: ContactListBinding): RecyclerView.ViewHolder(binding.root){

    fun setContact(contact: ContactModel){
        binding.tvName.setText(contact.name)
        binding.tvPhn.setText(contact.phoneNumber)
    }

    fun updateContact(contact: ContactModel){
        binding.btnEditSaveContact.setOnClickListener {
            binding.root.context.contentResolver.applyBatch(ContactsContract.AUTHORITY, ArrayList<ContentProviderOperation>().apply {
                // Update the contact's phone number
                add(ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI)
                    .withSelection(
                        ContactsContract.Data.RAW_CONTACT_ID + " = ?" +
                                " AND " + ContactsContract.Data.MIMETYPE + " = ?",
                        arrayOf(contact.id.toString(), ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                    )
                    .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, binding.tvPhn.text.toString())
                    .build())

                // Update the contact's name
                add(ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI)
                    .withSelection(
                        ContactsContract.Data.RAW_CONTACT_ID + " = ?" +
                                " AND " + ContactsContract.Data.MIMETYPE + " = ?",
                        arrayOf(contact.id.toString(), ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                    )
                    .withValue(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, binding.tvName.text.toString())
                    .build())
            })

            Toast.makeText(binding.root.context,"Contact updated",Toast.LENGTH_SHORT).show()
        }
    }
}

//        val contentValue = ContentValues()
//        Log.i("Abhi", contact.toString())
//
////        contentValue.put(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,contact.name) is not working, don't know why
//        contentValue.put(ContactsContract.CommonDataKinds.Phone.NUMBER, contact.phoneNumber)
//        val where = ContactsContract.Data.RAW_CONTACT_ID + " = ?" +
//                " AND " + ContactsContract.Data.MIMETYPE + " = ?"
//
//        val selectionArgs = arrayOf(
//            contact.id.toString(),
//            Phone.CONTENT_ITEM_TYPE
//        )
//
//        contentResolver.update(
//            ContactsContract.Data.CONTENT_URI,
//            contentValue,
//            where,
//            selectionArgs
//        )

//        this way we can only update one thing but by above way we can update multiple things