package com.example.androidlearning

import android.content.ContentProviderOperation
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidlearning.databinding.ActivityMain2Binding


class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    private val contactList = mutableListOf<ContactModel>()
    private lateinit var contactAdaptor: ContactAdaptor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.contactRecycleView.layoutManager = LinearLayoutManager(this)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onResume() {
        super.onResume()

        if (ContextCompat.checkSelfPermission(
                this,
                "android.permission.WRITE_CONTACTS"
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf("android.permission.WRITE_CONTACTS"), 0
            )
        }


        val uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        val projection = arrayOf(
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.Data.RAW_CONTACT_ID
        )

        val sortOrder = "${ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME} ASC"


        binding.getContactsBtn.setOnClickListener {
            contactList.clear()
            contentResolver.query(
                uri,
                projection,
                null,
                null,
                sortOrder
            )?.use { cursor ->
                val nameColumn =
                    cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                val numColumn = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                val idColum = cursor.getColumnIndex(ContactsContract.Data.RAW_CONTACT_ID)
                Log.i("Abhi", cursor.count.toString())
                while (cursor.moveToNext()) {
                    val contactName = cursor.getString(nameColumn)
                    val contactNumber = cursor.getString(numColumn)
                    val contactId = cursor.getLong(idColum)

                    Log.i("Abhi", contactName.toString())
                    Log.i("Abhi", contactNumber.toString())
                    Log.i("Abhi", contactId.toString())

                    contactList.add(ContactModel(contactName, contactNumber, contactId))
                }
            }
            contactAdaptor = ContactAdaptor(contactList)
            // did not used live data that why passing list after all operations
            binding.contactRecycleView.adapter = contactAdaptor
        }

        binding.btnNewSaveContact.setOnClickListener {
            addContact(binding.etvNameNew.text.toString(),binding.etvPhnNew.text.toString())
        }

    }

    fun addContact( name: String, number: String) {
        contentResolver.applyBatch(
            ContactsContract.AUTHORITY,
            ArrayList<ContentProviderOperation>().apply {

                add(
                    ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                        .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                        .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                        .build()
                )

                add(
                    ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                        .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                        .withValue(
                            ContactsContract.Data.MIMETYPE,
                            ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE
                        )
                        .withValue(
                            ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,
                            name
                        )
                        .build()
                )

                add(
                    ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                        .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                        .withValue(
                            ContactsContract.Data.MIMETYPE,
                            ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE
                        )
                        .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, number)
                        .withValue(
                            ContactsContract.CommonDataKinds.Phone.TYPE,
                            ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE
                        )
                        .build()
                )
            })
    }
}
