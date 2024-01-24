package dev.reprator.khatabook

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeViewModel(
    val gitHubRepo: GitHubRepo = GitHubRepo()
): ViewModel() {

    fun makeGitHubRequest(clientId:String,clientSecret:String,code:String) = viewModelScope.launch{
        val data = gitHubRepo.getAuthCode(
            clientId=clientId,
            clientSecret = clientSecret,
            code = code
        )
        if(data.isSuccessful){
            //Vikram:: G...eViewModel  D  GithubAccessToken(accessToken=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, tokenType=bearer)
            Log.d("Vikram:: GITHUB:: HomeViewModel",data.body().toString())

        }else{
            Log.d("Vikram:: GITHUB:: HomeViewModel", "NOT SUCCESSFUL")
        }
    }

}
