package com.tartaglia.unikk.use_cases.user_creation.carousel.account_name;

import android.content.Context;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.tartaglia.unikk.R;
import com.tartaglia.unikk.components.text_watcher.TextWatcherAdapter;
import com.tartaglia.unikk.domain.form.property.PropertyValidationResult;

public class AccountNameDefinitionView extends AccountNameDefinitionContract.IView implements AccountNameDefinitionContract {
  private final IViewModel mViewModel;
  private final EditText mEdtName;

  private final OnAccountNameDefinitionSubmit mSubmitListener;
  private final OnAccountNameDefinitionValidated mValidationListener;

  public AccountNameDefinitionView(
    Context context,
    AccountNameDefinitionModel model,
    OnAccountNameDefinitionSubmit submitListener,
    OnAccountNameDefinitionValidated validationListener) {

    super(context);
    LayoutInflater.from(context).inflate(R.layout.account_creation_name_definition_view, this, true);

    mValidationListener = validationListener;
    mSubmitListener = submitListener;
    mEdtName = this.findViewById(R.id.acnd_edt_user_identifier);
    mViewModel = new AccountNameDefinitionViewModel(this, model);

    init(model);
  }

  private void init(AccountNameDefinitionModel model) {
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

    mViewModel.start();
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

  @Override
  void submit() {
    mSubmitListener.onAccountNameDefinitionSubmit(mViewModel);
  }

  public interface OnAccountNameDefinitionSubmit {
    void onAccountNameDefinitionSubmit(AccountNameDefinitionContract.IViewModel state);
  }

  public interface OnAccountNameDefinitionValidated {
    void onAccountNameDefinitionValidated(boolean isValid);
  }
}
