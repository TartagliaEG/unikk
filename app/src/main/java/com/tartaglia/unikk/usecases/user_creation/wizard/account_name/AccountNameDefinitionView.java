package com.tartaglia.unikk.usecases.user_creation.wizard.account_name;

import android.content.Context;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.tartaglia.unikk.R;
import com.tartaglia.unikk.components.text_watcher.TextWatcherAdapter;
import com.tartaglia.unikk.lib.form.property.PropertyValidationResult;

public class AccountNameDefinitionView extends AccountNameDefinitionContract.IView implements AccountNameDefinitionContract {
  private final IViewModel mViewModel;

  private final EditText mEdtName;


  private OnAccountNameDefinitionValidated mValidationListener;

  public AccountNameDefinitionView(Context context) {
    super(context);
    LayoutInflater.from(context).inflate(R.layout.account_creation_name_definition_view, this, true);

    mEdtName = findViewById(R.id.acnd_edt_user_identifier);
    mEdtName.addTextChangedListener(new TextWatcherAdapter() {
      @Override
      public void afterTextChanged(Editable editable) {
        mViewModel.accountNameChanged(editable.toString());
      }
    });

    mEdtName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override
      public boolean onEditorAction(TextView textView, int action, KeyEvent keyEvent) {
        if (action == EditorInfo.IME_ACTION_GO)
          mViewModel.submitButtonTouched();
        return true;
      }
    });

    mViewModel = new AccountNameDefinitionViewModel(this);
  }

  @Override
  public void start(AccountNameDefinitionModel model, OnAccountNameDefinitionValidated validationListener) {
    mValidationListener = validationListener;
    mViewModel.start(model);
  }

  @Override
  public void stop() {
    mViewModel.stop();
  }

  @Override
  void showAccountName(String name) {
    mEdtName.setText(name);
  }

  @Override
  void accountNameValidated(PropertyValidationResult status) {
    mEdtName.setError(status.getErrorMessage(getContext()));
    if (status.hasError()) mEdtName.requestFocus();
  }

  @Override
  void formValidated(boolean isValid) {
    mValidationListener.onAccountNameDefinitionValidated(isValid);
  }


  public interface OnAccountNameDefinitionValidated {
    void onAccountNameDefinitionValidated(boolean isValid);
  }
}
