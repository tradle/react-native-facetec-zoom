export class VerificationPendingError extends Error {
  constructor(message) {
    super(message)
    this.name = 'VerificationPending'
  }
}

export class NotInitializedError extends Error {
  constructor(message) {
    super(message)
    this.name = 'NotInitialized'
  }
}

