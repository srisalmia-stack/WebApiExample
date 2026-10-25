package id.ac.polbeng.srisalmiapahma.webapiexample

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.polbeng.srisalmiapahma.webapiexample.databinding.ActivityListUsersBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListUsersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListUsersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvUsers.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvUsers.addItemDecoration(itemDecoration)

        getListUsers()
    }

    private fun getListUsers() {
        binding.progressBar.visibility = View.VISIBLE
        val client = ApiConfig.getApiService(this).getListUsers("1")
        client.enqueue(object : Callback<ResponseUser> {
            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                binding.progressBar.visibility = View.GONE
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        setUserData(responseBody.data ?: emptyList())
                    }
                } else {
                    Toast.makeText(this@ListUsersActivity, "onFailure: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(this@ListUsersActivity, "onFailure: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setUserData(users: List<DataItem>) {
        val adapter = UserAdapter(users)
        binding.rvUsers.adapter = adapter
    }
}
